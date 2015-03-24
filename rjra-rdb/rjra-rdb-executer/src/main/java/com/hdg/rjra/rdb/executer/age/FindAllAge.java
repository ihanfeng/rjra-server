package com.hdg.rjra.rdb.executer.age;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FindAllAge extends AbstractExecuter {

    static String sql = "select * from base_age";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}