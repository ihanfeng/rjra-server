package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class AccountFileProxyImpl extends BaseProxy implements IAccountFileProxy {

    private static String moduleName = "rdb-account-file";

    @Override
    public List<AccountFile> findAccountFileByIds(Long[] ids, Integer[] status) {
        return daoClient.invoke(moduleName, "findAccountFileByIds",
                new Object[]{ids, status});
    }

    @Override
    public AccountFile findAccountFileById(Long fileId) {
        return daoClient.invoke(moduleName, "findAccountFileById",
                new Object[]{fileId});
    }

    @Override
    public Long saveAccountFile(AccountFile file) {
        return daoClient.invoke(moduleName, "saveAccountFile",
                new Object[]{file});
    }
}
