package com.hdg.rjra.rdb.executer.manager;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/3/9 0009.
 */
public class FindManagerByNameAndPwd  extends AbstractExecuter {
    static String sql = "select * from account_manager where manager_name = ? and manager_pwd = ?";

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
