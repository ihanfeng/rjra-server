package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class SaveUserApplyWork extends AbstractExecuter {

    static String sql = "insert into user_apply_work(user_id,work_id,apply_time,user_apply_work_status) values (?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final UserApplyWork userApplyWork = (UserApplyWork) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, userApplyWork.getUserId());
                ps.setObject(2, userApplyWork.getWorkId());
                ps.setObject(3, new Date());
                ps.setObject(4, UserApplyWorkStatus.Active.getCode());
            }
        };
        return saveResultId(sql, pst);
    }
}
