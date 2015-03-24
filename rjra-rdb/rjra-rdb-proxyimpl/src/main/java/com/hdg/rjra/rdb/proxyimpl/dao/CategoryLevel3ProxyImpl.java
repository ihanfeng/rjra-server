package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel3Proxy;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel3;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CategoryLevel3ProxyImpl extends BaseProxy implements ICategoryLevel3Proxy {

    private static String moduleName = "rdb-category-level3";

    @Override
    public CategoryLevel3 findCategoryLevel3ByCategoryLevel3Id(Long categoryLevel3Id) {
        return daoClient.invoke(moduleName, "findCategoryLevel3ByCategoryLevel3Id",
                new Object[]{categoryLevel3Id});
    }

    @Override
    public List<CategoryLevel3> findCategoryLevel3ByCategoryLevel2Id(Long categoryLevel2Id) {
        return daoClient.invoke(moduleName, "findCategoryLevel3ByCategoryLevel2Id",
                new Object[]{categoryLevel2Id});
    }
}
