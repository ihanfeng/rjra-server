package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class AccountFileProxyImpl implements IAccountFileProxy {

    private static String moduleName = "rdb-account-file";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public List<AccountFile> findAccountFileByIds(Long[] ids, Integer[] status) {
        return daoClient.invoke(moduleName, "findAccountFileByIds",
                new Object[]{ids, status});
    }

    @Override
    public Long saveAccountFile(AccountFile file) {
        return daoClient.invoke(moduleName, "saveAccountFile",
                new Object[]{file});
    }
}
