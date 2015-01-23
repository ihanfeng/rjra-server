package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Rock on 2014/10/24.
 */
public class FindOneByProperty extends AbstractExecuter {
    private static Logger logger = LoggerFactory.getLogger(FindOneByProperty.class);

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
        logger.debug(sql);
        logger.debug(params.toString());
        List list = getJdbcTemplate().query(sql, params, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
