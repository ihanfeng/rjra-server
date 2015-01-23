package com.hdg.rjra.rdb.executer.rdbsql;


import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2014/10/27.
 */
public class ExecDDLSql extends AbstractExecuter {
    @Override
    public Object execute(Object[] params) {
        String sql = params[0].toString();
        getJdbcTemplate().execute(sql);
        return null;
    }
}
