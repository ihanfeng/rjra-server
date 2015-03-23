package com.hdg.rjra.server.model.param.category;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Administrator on 2015/3/23.
 */
public class CategoryParam extends BaseParam {

    private Long categoryLevel1Id;
    private Long categoryLevel12d;
    private Long categoryLevel13d;

    public Long getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(Long categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }

    public Long getCategoryLevel12d() {
        return categoryLevel12d;
    }

    public void setCategoryLevel12d(Long categoryLevel12d) {
        this.categoryLevel12d = categoryLevel12d;
    }

    public Long getCategoryLevel13d() {
        return categoryLevel13d;
    }

    public void setCategoryLevel13d(Long categoryLevel13d) {
        this.categoryLevel13d = categoryLevel13d;
    }
}
