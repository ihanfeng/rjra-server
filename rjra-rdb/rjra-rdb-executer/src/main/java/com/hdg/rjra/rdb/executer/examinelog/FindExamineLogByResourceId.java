package com.hdg.rjra.rdb.executer.examinelog;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FindExamineLogByResourceId extends AbstractExecuter {

    static String sql = "select * from examine_log where examine_log_resource = ?";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().queryForList(sql, params, rowMapper);
    }
}