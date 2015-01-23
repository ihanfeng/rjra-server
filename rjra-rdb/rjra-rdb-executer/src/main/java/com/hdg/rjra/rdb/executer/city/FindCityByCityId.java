package com.hdg.rjra.rdb.executer.city;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindCityByCityId extends AbstractExecuter {

    static String sql = "select * from base_city where city_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long cityId = (Long) params[0];
        Object[] objects = new Object[]{cityId};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
