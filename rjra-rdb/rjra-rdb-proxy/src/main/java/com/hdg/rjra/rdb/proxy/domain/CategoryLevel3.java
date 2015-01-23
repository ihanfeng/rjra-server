package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CategoryLevel3 implements Serializable {

    private Long categoryLevel3Id;
    private String categoryLevel3Name;
    private String categoryLevel3Desc;
    private Integer categoryLevel3Status;
    private Long categoryLevel2Id;

    public Long getCategoryLevel3Id() {
        return categoryLevel3Id;
    }

    public void setCategoryLevel3Id(Long categoryLevel3Id) {
        this.categoryLevel3Id = categoryLevel3Id;
    }

    public String getCategoryLevel3Name() {
        return categoryLevel3Name;
    }

    public void setCategoryLevel3Name(String categoryLevel3Name) {
        this.categoryLevel3Name = categoryLevel3Name;
    }

    public String getCategoryLevel3Desc() {
        return categoryLevel3Desc;
    }

    public void setCategoryLevel3Desc(String categoryLevel3Desc) {
        this.categoryLevel3Desc = categoryLevel3Desc;
    }

    public Integer getCategoryLevel3Status() {
        return categoryLevel3Status;
    }

    public void setCategoryLevel3Status(Integer categoryLevel3Status) {
        this.categoryLevel3Status = categoryLevel3Status;
    }

    public Long getCategoryLevel2Id() {
        return categoryLevel2Id;
    }

    public void setCategoryLevel2Id(Long categoryLevel2Id) {
        this.categoryLevel2Id = categoryLevel2Id;
    }
}
