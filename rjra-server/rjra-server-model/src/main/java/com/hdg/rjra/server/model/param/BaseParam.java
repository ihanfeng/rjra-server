package com.hdg.rjra.server.model.param;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/9 0009.
 */
public class BaseParam implements Serializable {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页条数
     */
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
