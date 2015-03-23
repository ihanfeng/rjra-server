package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.UUIDUtils;
import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.base.utils.UploadFileUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.FileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
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
    public Long saveAccountFile(AccountFileBo accountFileBo) {
        AccountFile accountFile = new AccountFile();
        ConversionUtils.conversion(accountFileBo, accountFile);
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

    @Override
    public AccountFileBo uploadFile(InputStream inputStream, String type, String key, String fileName, String fileType, String fileFormat) throws
            IOException {

        String cd = CustomizedPropertyConfigurer.getContextPropertyForString("upload_file_path");
        String dir = "upload/" + type + "/" + key + "/";
        String thumbnails = "_thumbnail";
        String random = UUIDUtils.randomUUID();
        String webPath = cd + dir + random + "." + fileFormat;
        String saveDbPath = dir + random + "." + fileFormat;
        String savePath = webPath;
        String webPathThumbnails = cd + dir + random + thumbnails + "." + fileFormat;
        String saveDbPathThumbnails = dir + random + thumbnails + "." + fileFormat;
        String savePathThumbnails = webPathThumbnails;
        UploadFileUtils.uploadFile(inputStream, cd + dir, savePath, savePathThumbnails, 156, 109);

        AccountFileBo accountFileBo = new AccountFileBo();
        accountFileBo.setFileFormat(fileFormat);
        accountFileBo.setFileUrl(saveDbPath);
        accountFileBo.setFileThumbnailUrl(saveDbPathThumbnails);
        accountFileBo.setFileName(fileName);
        accountFileBo.setFileType(Integer.valueOf(fileType));
        return accountFileBo;
    }
}
