package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.CategoryLevel3;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICategoryLevel3RdbService extends Serializable {

    public CategoryLevel3 findCategoryLevel3ByCategoryLevel3Id(Long categoryLevel3Id);
    
    public List<CategoryLevel3> findCategoryLevel3ByCategoryLevel2Id(Long categoryLevel2Id);
}
