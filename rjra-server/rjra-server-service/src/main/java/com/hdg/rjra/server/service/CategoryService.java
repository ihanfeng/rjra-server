package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel2;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel3;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public interface CategoryService {

    public CategoryLevel1 findCategoryLevel1ByCategoryLevel1Id(Long categoryLevel1Id);

    public List<CategoryLevel1> findAllCategoryLevel1();

    public CategoryLevel2 findCategoryLevel2ByCategoryLevel2Id(Long categoryLevel2Id);

    public List<CategoryLevel2> findCategoryLevel2ByCategoryLevel1Id(Long categoryLevel1Id);

    public CategoryLevel3 findCategoryLevel3ByCategoryLevel3Id(Long categoryLevel3Id);

    public List<CategoryLevel3> findCategoryLevel3ByCategoryLevel2Id(Long categoryLevel2Id);
}
