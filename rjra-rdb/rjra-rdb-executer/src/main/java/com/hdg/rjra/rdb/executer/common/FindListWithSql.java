package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Rock on 2014/10/24.
 */
public class FindListWithSql extends AbstractExecuter {
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    private String sql;

    public RowMapper getRowMapper() {
        return rowMapper;
    }

    public void setRowMapper(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    private RowMapper rowMapper;

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}
