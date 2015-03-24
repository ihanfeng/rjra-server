package com.hdg.rjra.rdb.executer.companyscale;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FindAllCompanyScale extends AbstractExecuter {

    static String sql = "select * from base_company_scale";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}