package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel2Proxy;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel2;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CategoryLevel2ProxyImpl implements ICategoryLevel2Proxy {

    private static String moduleName = "rdb-category-level2";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }
    @Override
    public CategoryLevel2 findCategoryLevel2ByCategoryLevel2Id(Long categoryLevel2Id) {
        return daoClient.invoke(moduleName, "findCategoryLevel2ByCategoryLevel2Id",
                new Object[]{categoryLevel2Id});
    }

    @Override
    public List<CategoryLevel2> findCategoryLevel2ByCategoryLevel1Id(Long categoryLevel1Id) {
        return daoClient.invoke(moduleName, "findCategoryLevel2ByCategoryLevel1Id",
                new Object[]{categoryLevel1Id});
    }
}
