package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

import java.util.List;

/**
 * Created by Rock on 2014/10/29.
 */
public class DeleteByIdList extends AbstractExecuter {
    private String tableName;
    private String propertyName;
    private Integer batch = 100;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    @Override
    public Object execute(Object[] params) {
        List ids = (List<Long>) params[0];
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from ");
        sql.append(tableName);
        sql.append(" where ");
        sql.append(propertyName);
        sql.append(" in (");
        sql.append(SqlUtils.appendPlaceholder(ids.size()));
        sql.append(" )");
        getJdbcTemplate().update(sql.toString(), ids.toArray());
        return null;
    }
}
