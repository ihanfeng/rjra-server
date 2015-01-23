package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.ISqlRdbProxy;
import com.hdg.rjra.rdb.proxy.mapping.SqlMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2014/10/21.
 */
public class RdbSqlProxyImpl implements ISqlRdbProxy {
    /**  描述   (@author: Rock) */
	    
	private static String moduleName = "rdb-rdbsql";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public <T> List<T> findListBySql(String sql, SqlMapping<T> sqlMapping) {
        return findListBySql(sql, null, sqlMapping);
    }

    @Override
    public <T> List<T> findListBySql(String sql, List<Object> params, SqlMapping<T> sqlMapping) {
        Object[] args = null;
        if (params != null) {
            args = new Object[2];
            args[0] = sql;
            args[1] = params;
        } else {
            args = new Object[1];
            args[0] = sql;
        }
        List<T> resultList = new ArrayList<>();
        List<Map<String, Object>> sqlResultList = daoClient.invoke(moduleName, "findListBySql", args);
        for (Map<String, Object> stringObjectMap : sqlResultList) {
            resultList.add(sqlMapping.mapping(stringObjectMap));
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> findListBySql(String sql) {
        Object[] args = new Object[1];
        args[0] = sql;
        return daoClient.invoke(moduleName, "findListBySql", args);
    }

    @Override
    public List<Map<String, Object>> findListBySql(String sql, List<Object> params) {
        Object[] args = new Object[2];
        args[0] = sql;
        args[1] = params;
        return daoClient.invoke(moduleName, "findListBySql", args);
    }

    @Override
    public void execDDLSql(String sql) {
        daoClient.invoke(moduleName, "execDDLSql", new Object[]{sql});
    }
}
