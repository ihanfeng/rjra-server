package com.hdg.rjra.rdb.executer.usercollectuser;

import com.hdg.rjra.base.enumerate.UserCollectUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserCollectUserByUserId extends AbstractExecuter {
    static String sql = "select * from user_collect_user where user_id = ? and user_collect_user_status=" + UserCollectUserStatus.Active.getCode();
    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}