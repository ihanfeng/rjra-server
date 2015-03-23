package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CategoryLevel1 implements BaseDomain {
    private Long categoryLevel1Id;
    private String categoryLevel1Name;
    private String categoryLevel1Desc;
    private Integer categoryLevel1Status;

    public Long getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(Long categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }

    public String getCategoryLevel1Name() {
        return categoryLevel1Name;
    }

    public void setCategoryLevel1Name(String categoryLevel1Name) {
        this.categoryLevel1Name = categoryLevel1Name;
    }

    public String getCategoryLevel1Desc() {
        return categoryLevel1Desc;
    }

    public void setCategoryLevel1Desc(String categoryLevel1Desc) {
        this.categoryLevel1Desc = categoryLevel1Desc;
    }

    public Integer getCategoryLevel1Status() {
        return categoryLevel1Status;
    }

    public void setCategoryLevel1Status(Integer categoryLevel1Status) {
        this.categoryLevel1Status = categoryLevel1Status;
    }
}
