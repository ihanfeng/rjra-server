package com.hdg.rjra.base.enumerate;

/**
 * Created by Administrator on 2015/3/25.
 */
public enum  DataResourceType {
    MobileClient(0),
    Crawl(1);

    private int code;
    private DataResourceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
