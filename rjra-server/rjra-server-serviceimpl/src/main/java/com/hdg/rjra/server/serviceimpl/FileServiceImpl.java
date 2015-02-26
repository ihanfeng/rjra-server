package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
