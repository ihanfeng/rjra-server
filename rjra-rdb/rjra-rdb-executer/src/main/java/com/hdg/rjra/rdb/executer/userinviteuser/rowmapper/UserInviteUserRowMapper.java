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
        re.setInviteDate(rs.getTimestamp("invite_time"));
        return re;
    }
}
