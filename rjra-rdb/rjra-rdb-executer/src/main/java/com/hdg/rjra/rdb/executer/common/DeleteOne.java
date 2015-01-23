package com.hdg.rjra.rdb.executer.common;


import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.springframework.dao.DataAccessException;

/**
 * Created by Rock on 2014/10/24.
 */
public class DeleteOne extends AbstractExecuter {

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
        String sql = "delete  from `" + tableName + "` where " + propertyName + " = ?";
        try {
            getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
