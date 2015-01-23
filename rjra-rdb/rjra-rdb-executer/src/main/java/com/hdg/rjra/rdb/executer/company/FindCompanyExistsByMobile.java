package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class FindCompanyExistsByMobile extends AbstractExecuter {
    static String sql = "select count(1) cnt from account_company where company_mobile = ?";
    @Override
    public Object execute(Object[] params) {
        Object count = getJdbcTemplate().queryForMap(sql, params).get("cnt");
        return Integer.parseInt(String.valueOf(count));
    }
}
