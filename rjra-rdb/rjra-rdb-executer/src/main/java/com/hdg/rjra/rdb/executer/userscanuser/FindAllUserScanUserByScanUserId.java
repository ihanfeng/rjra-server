package com.hdg.rjra.rdb.executer.userscanuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserScanUserByScanUserId extends AbstractExecuter {

    static String sql = "select * from user_scan_user where scan_user_id=?";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}
