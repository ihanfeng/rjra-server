package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.FileService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    IAccountFileProxy accountFileProxy;
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);


    @Override
    public Long upload(MultipartFile file, String type, String path, String key, String fileName, String fileType, String fileFormat) throws IOException {
        AccountFile accountFile = uploadFile(file, type, path, key, fileName, fileType, fileFormat);
        return accountFileProxy.saveAccountFile(accountFile);
    }

    @Override
    public List<AccountFileBo> findAccountFileByIds(Long[] fileIds) {
        List<AccountFile> accountFileList = accountFileProxy.findAccountFileByIds(fileIds, new Integer[]{FileStatus.Display.getCode()});
        List<AccountFileBo> accountFileBoList = new ArrayList<AccountFileBo>();
        for (AccountFile accountFile : accountFileList) {
            AccountFileBo bo = new AccountFileBo();
            ConversionUtils.conversion(accountFile, bo);
            accountFileBoList.add(bo);
        }
        return accountFileBoList;
    }

    @Override
    public AccountFileBo findAccountFileById(Long fileId) {
        AccountFile accountFile = accountFileProxy.findAccountFileById(fileId);
        AccountFileBo bo = new AccountFileBo();
        ConversionUtils.conversion(accountFile, bo);
        return bo;
    }

    private AccountFile uploadFile(MultipartFile file, String type, String path, String key, String fileName, String fileType, String fileFormat) throws IOException {

        String cd = CustomizedPropertyConfigurer.getContextPropertyForString("upload_file_path");
        String dir = "upload/" + type + "/" + key + "/";
        String thumbnails = "_thumbnail";
        String webPath = cd + dir + fileName + "." + fileFormat;
        String saveDbPath = dir + fileName + "." + fileFormat;
        File newfile = new File(path + cd + dir);
        String savePath = path + webPath;
        String webPathThumbnails = cd + dir + fileName + thumbnails + "." + fileFormat;
        String saveDbPathThumbnails = dir + fileName + thumbnails + "." + fileFormat;
        String savePathThumbnails = path + webPathThumbnails;
        File files = new File(savePath);
        File filesThumbnails = new File(savePathThumbnails);
        if (!newfile.exists()) {
            newfile.mkdirs();
            files.createNewFile();
            filesThumbnails.createNewFile();
        }

        FileOutputStream outputStream = new FileOutputStream(savePath);
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        byte[] buffer = new byte[1024 * 1024];

        int c = 0;
        while ((c = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, c);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        //生成缩略图
        Thumbnails.of(savePath).forceSize(156, 109).toFile(filesThumbnails);

        AccountFile accountFile = new AccountFile();
        accountFile.setFileFormat(fileFormat);
        accountFile.setFileUrl(saveDbPath);
        accountFile.setFileThumbnailUrl(saveDbPathThumbnails);
        accountFile.setFileName(fileName);
        accountFile.setFileType(Integer.valueOf(fileType));
        return accountFile;
    }

    private File getImageFile(String fileName, String contextPath) {

        try {
            String endType = fileName.substring(fileName.lastIndexOf("."));
            String name = UUID.randomUUID().toString() + endType;
            String sep = System.getProperty("file.separator");
            StringBuffer fileNames = new StringBuffer();
            fileNames.append(contextPath).append("upload").append(sep);
            File fileDir = new File(fileNames.toString());
            if (!fileDir.exists()) {
                if (!fileDir.mkdirs()) {
                    return null;
                }
            }

            fileNames.append(name);

            File tmpFile = new File(fileNames.toString());
            return tmpFile;
        } catch (IllegalStateException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
