package com.hdg.rjra.rdb.executer.userinviteuser.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserInviteUserRowMapper implements RowMapper<UserInviteUser> {
    @Override
    public UserInviteUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserInviteUser re = new UserInviteUser();
        re.setInviteId(rs.getLong("invite_id"));
        re.setUserId(rs.getLong("user_id"));
        re.setInviteUserId(rs.getLong("invite_user_id"));
        re.setInviteTime(rs.getTimestamp("invite_time"));
        re.setInterviewTime(rs.getTimestamp("interview_time"));
        re.setInterviewAddress(rs.getString("interview_address"));
        re.setInterviewer(rs.getString("interviewer"));
        re.setInterviewMobile(rs.getString("interview_mobile"));
        re.setInterviewDesc(rs.getString("interview_desc"));
        re.setApplyId(rs.getLong("apply_id"));
        re.setWorkId(rs.getLong("work_id"));
        re.setUserInviteUserStatus(rs.getInt("user_invite_user_status"));
        return re;
    }
}
