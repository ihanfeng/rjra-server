package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class FindCompanyByMobileAndPwd extends AbstractExecuter {
    static String sql = "select * from account_company where company_mobile = ? and company_pwd=?";
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
