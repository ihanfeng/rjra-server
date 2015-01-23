package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Rock on 2014/10/24.
 */
public class FindOneWithSql extends AbstractExecuter {
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
        List list = getJdbcTemplate().query(sql, params, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
