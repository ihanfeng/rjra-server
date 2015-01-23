package com.hdg.rjra.rdb.executer.province;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindProvinceByProvinceId extends AbstractExecuter {

    static String sql = "select * from base_province where province_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long provinceId = (Long) params[0];
        Object[] objects = new Object[]{provinceId};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
