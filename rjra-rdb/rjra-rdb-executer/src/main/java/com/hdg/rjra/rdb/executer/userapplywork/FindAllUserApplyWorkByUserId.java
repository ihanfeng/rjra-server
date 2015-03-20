package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserApplyWorkByUserId extends AbstractExecuter {
    static String sql = "select * from user_apply_work where user_id = ? and user_apply_work_status = " + UserApplyWorkStatus.Active.getCode();
    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}
