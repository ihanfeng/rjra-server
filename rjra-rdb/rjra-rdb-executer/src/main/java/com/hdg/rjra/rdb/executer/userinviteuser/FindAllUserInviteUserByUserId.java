package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.base.enumerate.UserInviteUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserInviteUserByUserId extends AbstractExecuter {
    static String sql = "select * from user_invite_user where user_id = ? and user_invite_user_status=" + UserInviteUserStatus.Active.getCode();

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}