package com.hdg.rjra.rdb.executer.userscanuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.UserScanUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2015/3/20.
 */
public class SaveUserScanUser extends AbstractExecuter {

    static String sql = "insert into user_scan_user(user_id,scan_user_id,scan_time) values (?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final UserScanUser userScanUser = (UserScanUser) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, userScanUser.getUserId());
                ps.setObject(2, userScanUser.getScanUserId());
                ps.setObject(3, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
