package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.BatchPstAssign;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class BatchSaveUserInviteUser extends AbstractExecuter {
    private String sql = "insert into user_invite_user(user_id,invite_user_id,invite_time) values (?,?,?)";

    @Override
    public Object execute(Object[] params) {
        List<UserInviteUser> publishResultRecords = (List<UserInviteUser>) params[0];
        return batchSaveResultId(sql, new BatchPstAssign<UserInviteUser>() {
            @Override
            public void setParam(PreparedStatement ps, UserInviteUser userInviteUser) throws SQLException {
                ps.setObject(1, userInviteUser.getUserId());
                ps.setObject(2, userInviteUser.getInviteUserId());
                ps.setObject(3, new Date());
            }
        }, publishResultRecords);
    }
}
