package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IWelfareProxy;
import com.hdg.rjra.rdb.proxy.domain.Welfare;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class WelfareProxyImpl implements IWelfareProxy {

    private static String moduleName = "rdb-welface";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public List<Welfare> findAllWelfare() {
        return daoClient.invoke(moduleName, "findAllWelfare",
                new Object[]{});
    }
}
