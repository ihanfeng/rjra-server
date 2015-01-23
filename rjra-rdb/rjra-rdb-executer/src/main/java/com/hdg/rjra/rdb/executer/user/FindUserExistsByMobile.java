package com.hdg.rjra.rdb.executer.user;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class FindUserExistsByMobile extends AbstractExecuter {
    static String sql = "select count(1) cnt from account_user where user_mobile = ?";
    @Override
    public Object execute(Object[] params) {
        Object count = getJdbcTemplate().queryForMap(sql, params).get("cnt");
        return Integer.parseInt(String.valueOf(count));
    }
}
