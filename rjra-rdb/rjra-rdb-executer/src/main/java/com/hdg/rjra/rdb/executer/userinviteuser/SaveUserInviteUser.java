package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class SaveUserInviteUser extends AbstractExecuter {

    static String sql = "insert into user_invite_user(user_id,invite_user_id,invite_time) values (?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final UserInviteUser userInviteUser = (UserInviteUser) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, userInviteUser.getUserId());
                ps.setObject(2, userInviteUser.getInviteUserId());
                ps.setObject(3, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
