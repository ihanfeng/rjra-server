package com.hdg.rjra.rdb.executer.usercollectwork;

import com.hdg.rjra.base.enumerate.UserCollectUserStatus;
import com.hdg.rjra.base.enumerate.UserCollectWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserCollectWorkByUserId extends AbstractExecuter {
    static String sql = "select * from user_collect_work where user_id = ? and user_collect_work_status=" + UserCollectWorkStatus.Active.getCode();
    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}