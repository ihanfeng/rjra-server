package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.base.enumerate.UserInviteUserStatus;
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

    private String sql = "insert into user_invite_user(user_id,invite_user_id,invite_time," +
            "user_invite_user_status,interview_time,interview_address,interview_mobile,interview_desc," +
            "apply_id,work_id,interviewer) values (?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final UserInviteUser userInviteUser = (UserInviteUser) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, userInviteUser.getUserId());
                ps.setObject(2, userInviteUser.getInviteUserId());
                ps.setObject(3, new Date());
                ps.setObject(4, UserInviteUserStatus.Active.getCode());
                ps.setObject(5, userInviteUser.getInterviewTime());
                ps.setObject(6, userInviteUser.getInterviewAddress());
                ps.setObject(7, userInviteUser.getInterviewMobile());
                ps.setObject(8, userInviteUser.getInterviewDesc());
                ps.setObject(9, userInviteUser.getApplyId());
                ps.setObject(10, userInviteUser.getWorkId());
                ps.setObject(11, userInviteUser.getInterviewer());
            }
        };
        return saveResultId(sql, pst);
    }
}
