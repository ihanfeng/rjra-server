package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.base.enumerate.UserInviteUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class FindUserInviteUserByApplyId extends AbstractExecuter {
    static String sql = "select * from user_invite_user where apply_id = ? and user_invite_user_status=" + UserInviteUserStatus.Active.getCode();
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
