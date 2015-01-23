package com.hdg.rjra.rdb.executer.province;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAllProvince extends AbstractExecuter {

    static String sql = "select * from base_province";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}
