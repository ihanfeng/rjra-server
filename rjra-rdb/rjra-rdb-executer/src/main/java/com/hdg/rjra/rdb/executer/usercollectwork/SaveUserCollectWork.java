package com.hdg.rjra.rdb.executer.usercollectwork;

import com.hdg.rjra.base.enumerate.UserCollectWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class SaveUserCollectWork extends AbstractExecuter {

    static String sql = "insert into user_collect_work(user_id,work_id,collect_time,user_collect_work_status) values (?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final UserCollectWork userCollectWork = (UserCollectWork) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, userCollectWork.getUserId());
                ps.setObject(2, userCollectWork.getWorkId());
                ps.setObject(3, new Date());
                ps.setObject(4, UserCollectWorkStatus.Active.getCode());
            }
        };
        return saveResultId(sql, pst);
    }
}
