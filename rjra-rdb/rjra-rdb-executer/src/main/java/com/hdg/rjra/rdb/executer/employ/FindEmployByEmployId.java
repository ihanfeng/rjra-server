package com.hdg.rjra.rdb.executer.employ;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class FindEmployByEmployId extends AbstractExecuter {
    static String sql = "select * from rec_employ where employ_id = ?";
    @Override
    public Object execute(Object[] params) {
        Long employId = (Long) params[0];
        Object[] objects = new Object[]{employId};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
