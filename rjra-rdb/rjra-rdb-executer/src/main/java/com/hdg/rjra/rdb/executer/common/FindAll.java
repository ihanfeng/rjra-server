package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Jaky on 2014/10/24.
 */
public class FindAll extends AbstractExecuter {
    public RowMapper getRowMapper() {
        return rowMapper;
    }

    public void setRowMapper(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    private RowMapper rowMapper;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;
    @Override
    public Object execute(Object[] params) {
        String sql = "select * from " + tableName;
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}
