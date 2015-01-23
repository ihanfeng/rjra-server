package com.hdg.rjra.rdb.executer.workexperience;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAllWorkExperience extends AbstractExecuter {

    static String sql = "select * from base_work_experience";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}
