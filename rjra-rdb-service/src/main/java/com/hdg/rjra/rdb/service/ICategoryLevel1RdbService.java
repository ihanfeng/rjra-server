package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.CategoryLevel1;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICategoryLevel1RdbService extends Serializable {

    public Object findCategoryLevel1ByCategoryLevel1Id(Long categoryLevel1Id);

    public List<CategoryLevel1> findAllCategoryLevel1();
}
