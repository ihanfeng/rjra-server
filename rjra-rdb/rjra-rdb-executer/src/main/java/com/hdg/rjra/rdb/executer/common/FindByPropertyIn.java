package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Rock on 2014/10/24.
 */
public class FindByPropertyIn extends AbstractExecuter {
    private RowMapper rowMapper;
    private String propertyName;
    private String tableName;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public RowMapper getRowMapper() {
        return rowMapper;
    }

    public void setRowMapper(RowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public Object execute(Object[] params) {
        Object value = params[0];
        Object[] sqlParam = null;
        if (value instanceof List) {
            sqlParam = ((List) value).toArray();
        } else if (value instanceof String) {
            sqlParam = ((String) value).split(",");
        } else if (value instanceof Object[]) {
            sqlParam = (Object[]) value;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(tableName);
        sb.append(" where ");
        sb.append(propertyName).append(" in (");
        sb.append(SqlUtils.appendPlaceholder(sqlParam.length));
        sb.append(")");

        return getJdbcTemplate().query(sb.toString(), sqlParam, rowMapper);
    }
}
