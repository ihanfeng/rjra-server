package com.hdg.rjra.rdb.executer.usercollectwork;

import com.hdg.rjra.base.enumerate.UserCollectWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class FindUserCollectWorkByUserIdAndWorkId extends AbstractExecuter {
    static String sql = "select * from user_collect_work where user_id = ? and work_id=? and user_collect_work_status=" + UserCollectWorkStatus.Active.getCode();
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
