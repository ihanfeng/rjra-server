package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class FindCompanyByCompanyId extends AbstractExecuter {
    static String sql = "select * from user_company where company_id = ?";
    @Override
    public Object execute(Object[] params) {
        Long companyId = (Long) params[0];
        Object[] objects = new Object[]{companyId};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
