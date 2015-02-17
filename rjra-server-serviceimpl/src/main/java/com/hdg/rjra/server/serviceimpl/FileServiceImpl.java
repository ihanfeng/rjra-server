package com.hdg.rjra.server.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.rdb.domain.AccountFile;
import com.hdg.rjra.rdb.service.IAccountFileRdbService;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    IAccountFileRdbService accountFileRdbService;
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public Long saveAccountFile(AccountFileBo accountFileBo) {
        AccountFile accountFile = new AccountFile();
        ConversionUtils.conversion(accountFileBo, accountFile);
        return accountFileRdbService.saveAccountFile(accountFile);
    }

    @Override
    public List<AccountFileBo> findAccountFileByIds(Long[] fileIds) {
        List<AccountFile> accountFileList = accountFileRdbService.findAccountFileByIds(fileIds, new Integer[]{FileStatus.Display.getCode()});
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
        AccountFile accountFile = accountFileRdbService.findAccountFileById(fileId);
        AccountFileBo bo = new AccountFileBo();
        ConversionUtils.conversion(accountFile, bo);
        return bo;
    }


}
