package com.hdg.rjra.rdb.executer.usercollectuser;

import com.hdg.rjra.base.enumerate.UserCollectUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class SaveUserCollectUser extends AbstractExecuter {

    static String sql = "insert into user_collect_user(user_id,collect_user_id,collect_time,user_collect_user_status) values (?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final UserCollectUser userCollectUser = (UserCollectUser) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, userCollectUser.getUserId());
                ps.setObject(2, userCollectUser.getCollectUserId());
                ps.setObject(3, new Date());
                ps.setObject(4, UserCollectUserStatus.Active.getCode());
            }
        };
        return saveResultId(sql, pst);
    }
}
