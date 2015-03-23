package com.hdg.rjra.server.model.param.category;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Administrator on 2015/3/23.
 */
public class CategoryParam extends BaseParam {

    private Long categoryLevel1Id;
    private Long categoryLevel2Id;
    private Long categoryLevel3Id;

    public Long getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(Long categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }

    public Long getCategoryLevel2Id() {
        return categoryLevel2Id;
    }

    public void setCategoryLevel2Id(Long categoryLevel2Id) {
        this.categoryLevel2Id = categoryLevel2Id;
    }

    public Long getCategoryLevel3Id() {
        return categoryLevel3Id;
    }

    public void setCategoryLevel3Id(Long categoryLevel3Id) {
        this.categoryLevel3Id = categoryLevel3Id;
    }
}
