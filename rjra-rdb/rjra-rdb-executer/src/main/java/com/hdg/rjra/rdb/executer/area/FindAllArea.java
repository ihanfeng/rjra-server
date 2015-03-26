package com.hdg.rjra.rdb.executer.area;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAllArea extends AbstractExecuter {

    static String sql = "select * from base_area";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, rowMapper);
    }
}
