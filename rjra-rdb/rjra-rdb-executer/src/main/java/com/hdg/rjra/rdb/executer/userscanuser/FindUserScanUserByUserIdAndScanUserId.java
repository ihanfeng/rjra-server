package com.hdg.rjra.rdb.executer.userscanuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindUserScanUserByUserIdAndScanUserId extends AbstractExecuter {

    static String sql = "select * from user_scan_user where user_id = ? and scan_user_id=?";
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
