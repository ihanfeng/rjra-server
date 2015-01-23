package com.hdg.rjra.rdb.executer.area;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAreaByCityId extends AbstractExecuter {

    static String sql = "select * from base_area where city_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long cityId = (Long) params[0];
        return getJdbcTemplate().query(sql, new Object[]{cityId}, rowMapper);
    }
}