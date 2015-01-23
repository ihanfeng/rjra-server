package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.mapping.SqlMapping;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2014/10/21.
 */
public interface ISqlRdbProxy extends Serializable {

    /**
     * @param sql
     * @return
     */
    public List<Map<String, Object>> findListBySql(String sql);

    public List<Map<String, Object>> findListBySql(String sql, List<Object> params);

    public <T> List<T> findListBySql(String sql, List<Object> params, SqlMapping<T> sqlMapping);

    public <T> List<T> findListBySql(String sql, SqlMapping<T> sqlMapping);

    public void execDDLSql(String sql);
}
