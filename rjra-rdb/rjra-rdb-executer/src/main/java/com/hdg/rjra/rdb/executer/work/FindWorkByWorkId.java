package com.hdg.rjra.rdb.executer.work;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class FindWorkByWorkId extends AbstractExecuter {
    static String sql = "select * from user_work where work_id = ?";
    @Override
    public Object execute(Object[] params) {
        List list = getJdbcTemplate().query(sql, params, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}