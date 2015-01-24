package com.hdg.rjra.rdb.executer.resume;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/24 0024.
 */
public class FindResumeByResumeId extends AbstractExecuter {
    static String sql = "select * from user_resume where resume_id = ?";
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
