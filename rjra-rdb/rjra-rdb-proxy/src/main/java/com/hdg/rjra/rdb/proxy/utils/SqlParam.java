package com.hdg.rjra.rdb.proxy.utils;

import java.util.List;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class SqlParam {
    private String sql;
    private String countSql;

    private List<Object> objects;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getCountSql() {
        return countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
