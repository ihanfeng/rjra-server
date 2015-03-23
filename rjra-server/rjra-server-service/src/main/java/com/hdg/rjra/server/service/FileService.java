package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.file.AccountFileBo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface FileService {

    public Long saveAccountFile(AccountFileBo accountFileBo);

    public List<AccountFileBo> findAccountFileByIds(Long[] fileIds);

    public AccountFileBo findAccountFileById(Long fileId);

    public AccountFileBo uploadFile(InputStream inputStream, String type, String key, String fileName, String fileType, String fileFormat) throws
            IOException;
}
