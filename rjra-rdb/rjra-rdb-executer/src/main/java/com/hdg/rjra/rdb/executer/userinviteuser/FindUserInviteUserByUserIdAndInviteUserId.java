package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class FindUserInviteUserByUserIdAndInviteUserId extends AbstractExecuter {
    static String sql = "select * from user_invite_user where user_id = ? and invite_user_id=?";
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
