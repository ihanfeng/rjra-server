package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel1Proxy;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CategoryLevel1ProxyImpl extends BaseProxy implements ICategoryLevel1Proxy {

    private static String moduleName = "rdb-category-level1";

    @Override
    public CategoryLevel1 findCategoryLevel1ByCategoryLevel1Id(Long categoryLevel1Id) {
        return daoClient.invoke(moduleName, "findCategoryLevel1ByCategoryLevel1Id",
                new Object[]{categoryLevel1Id});
    }

    @Override
    public List<CategoryLevel1> findAllCategoryLevel1() {
        return daoClient.invoke(moduleName, "findAllCategoryLevel1",
                new Object[]{});
    }
}
