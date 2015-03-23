package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class FindUserApplyWorkByApplyId extends AbstractExecuter {
    static String sql = "select * from user_apply_work where apply_id = ? and user_apply_work_status = " + UserApplyWorkStatus.Active.getCode();
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
