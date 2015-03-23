package com.hdg.rjra.rdb.executer.city;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindCityByProvinceId extends AbstractExecuter {

    static String sql = "select * from base_city where province_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long provinceId = (Long) params[0];
        return getJdbcTemplate().query(sql, new Object[]{provinceId}, rowMapper);
    }
}
