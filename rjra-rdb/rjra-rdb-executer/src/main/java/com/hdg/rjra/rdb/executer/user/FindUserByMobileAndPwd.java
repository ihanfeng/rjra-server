package com.hdg.rjra.rdb.executer.user;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class FindUserByMobileAndPwd extends AbstractExecuter {
    static String sql = "select * from account_user where user_mobile = ? and user_pwd = ?";
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
