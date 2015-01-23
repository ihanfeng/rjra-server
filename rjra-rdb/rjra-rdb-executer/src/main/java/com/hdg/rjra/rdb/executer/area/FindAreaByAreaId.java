package com.hdg.rjra.rdb.executer.area;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAreaByAreaId extends AbstractExecuter {

    static String sql = "select * from base_area where area_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long areaId = (Long) params[0];
        Object[] objects = new Object[]{areaId};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
