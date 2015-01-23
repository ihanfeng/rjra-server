package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICategoryLevel1Proxy extends Serializable {

    public CategoryLevel1 findCategoryLevel1ByCategoryLevel1Id(Long categoryLevel1Id);

    public List<CategoryLevel1> findAllCategoryLevel1();
}
