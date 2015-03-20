package com.hdg.rjra.rdb.executer.usercollectuser;

import com.hdg.rjra.base.enumerate.UserCollectUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class FindUserCollectUserByUserIdAndCollectUserId extends AbstractExecuter {
    static String sql = "select * from user_collect_user where user_id = ? and collect_user_id=? and user_collect_user_status=" + UserCollectUserStatus.Active.getCode();
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
