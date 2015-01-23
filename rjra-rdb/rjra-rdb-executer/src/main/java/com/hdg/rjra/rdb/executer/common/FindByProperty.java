package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Rock on 2014/10/24.
 */
public class FindByProperty extends AbstractExecuter {


    public RowMapper getRowMapper() {
        return rowMapper;
    }

    public void setRowMapper(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    private RowMapper rowMapper;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    private String propertyName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;
    @Override
    public Object execute(Object[] params) {
        String sql = "select * from " + tableName + " where " + propertyName + " = ?";
        return getJdbcTemplate().query(sql,params,rowMapper);
    }
}
