package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.CategoryLevel2;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICategoryLevel2RdbSercice extends Serializable {

    public CategoryLevel2 findCategoryLevel2ByCategoryLevel2Id(Long categoryLevel2Id);
    
    public List<CategoryLevel2> findCategoryLevel2ByCategoryLevel1Id(Long categoryLevel1Id);
}
