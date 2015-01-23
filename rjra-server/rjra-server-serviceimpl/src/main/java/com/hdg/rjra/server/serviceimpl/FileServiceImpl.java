package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.base.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    private AccountFile uploadFile(MultipartFile file, String type, String path, String key, String fileName, String fileType, String fileFormat) throws IOException {

        String cd = CustomizedPropertyConfigurer.getContextPropertyForString("upload_file_path");
        String dir = "upload/" + type + "/" + key + "/";
        String webPath = cd + dir + fileName + "." + fileFormat;
        String saveDbPath = dir + fileName + "." + fileFormat;
        File newfile = new File(path + cd + dir);
        String savePath = path + webPath;
        File files = new File(savePath);
        if (!newfile.exists()) {
            newfile.mkdirs();
            files.createNewFile();
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
        AccountFile accountFile = new AccountFile();
        accountFile.setFileFormat(fileFormat);
        accountFile.setFileUrl(saveDbPath);
        accountFile.setFileName(fileName);
        accountFile.setFileType(Integer.valueOf(fileType));
        return accountFile;
    }
}
