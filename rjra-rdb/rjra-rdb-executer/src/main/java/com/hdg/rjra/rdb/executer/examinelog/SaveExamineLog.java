package com.hdg.rjra.rdb.executer.examinelog;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/24.
 */
public class SaveExamineLog extends AbstractExecuter {

    static String sql = "select * from base_company_type";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}