package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CategoryLevel2 implements Serializable {
    private Long categoryLevel2Id;
    private String categoryLevel2Name;
    private String categoryLevel2Desc;
    private Integer categoryLevel2Status;
    private Long categoryLevel1Id;

    public Long getCategoryLevel2Id() {
        return categoryLevel2Id;
    }

    public void setCategoryLevel2Id(Long categoryLevel2Id) {
        this.categoryLevel2Id = categoryLevel2Id;
    }

    public String getCategoryLevel2Name() {
        return categoryLevel2Name;
    }

    public void setCategoryLevel2Name(String categoryLevel2Name) {
        this.categoryLevel2Name = categoryLevel2Name;
    }

    public String getCategoryLevel2Desc() {
        return categoryLevel2Desc;
    }

    public void setCategoryLevel2Desc(String categoryLevel2Desc) {
        this.categoryLevel2Desc = categoryLevel2Desc;
    }

    public Integer getCategoryLevel2Status() {
        return categoryLevel2Status;
    }

    public void setCategoryLevel2Status(Integer categoryLevel2Status) {
        this.categoryLevel2Status = categoryLevel2Status;
    }

    public Long getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(Long categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }
}
