package com.hdg.rjra.rdb.utils;

import java.util.List;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class WhereAndSqlParam {
    private String sql;

    private List<Object> objects;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
