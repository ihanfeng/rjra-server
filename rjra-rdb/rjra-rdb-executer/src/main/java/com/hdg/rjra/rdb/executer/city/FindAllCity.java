package com.hdg.rjra.rdb.executer.city;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAllCity extends AbstractExecuter {

    static String sql = "select * from base_city";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, rowMapper);
    }
}
