package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel1Proxy;
import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel2Proxy;
import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel3Proxy;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel2;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel3;
import com.hdg.rjra.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ICategoryLevel1Proxy categoryLevel1Proxy;
    @Autowired
    ICategoryLevel2Proxy categoryLevel2Proxy;
    @Autowired
    ICategoryLevel3Proxy categoryLevel3Proxy;

    @Override
    public CategoryLevel1 findCategoryLevel1ByCategoryLevel1Id(Long categoryLevel1Id) {
        return categoryLevel1Proxy.findCategoryLevel1ByCategoryLevel1Id(categoryLevel1Id);
    }

    @Override
    public List<CategoryLevel1> findAllCategoryLevel1() {
        return categoryLevel1Proxy.findAllCategoryLevel1();
    }

    @Override
    public CategoryLevel2 findCategoryLevel2ByCategoryLevel2Id(Long categoryLevel2Id) {
        return categoryLevel2Proxy.findCategoryLevel2ByCategoryLevel2Id(categoryLevel2Id);
    }

    @Override
    public List<CategoryLevel2> findCategoryLevel2ByCategoryLevel1Id(Long categoryLevel1Id) {
        return categoryLevel2Proxy.findCategoryLevel2ByCategoryLevel1Id(categoryLevel1Id);
    }

    @Override
    public CategoryLevel3 findCategoryLevel3ByCategoryLevel3Id(Long categoryLevel3Id) {
        return categoryLevel3Proxy.findCategoryLevel3ByCategoryLevel3Id(categoryLevel3Id);
    }

    @Override
    public List<CategoryLevel3> findCategoryLevel3ByCategoryLevel2Id(Long categoryLevel2Id) {
        return categoryLevel3Proxy.findCategoryLevel3ByCategoryLevel2Id(categoryLevel2Id);
    }
}
