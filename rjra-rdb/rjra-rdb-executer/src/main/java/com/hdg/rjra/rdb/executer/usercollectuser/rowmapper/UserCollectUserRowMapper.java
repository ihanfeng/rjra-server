package com.hdg.rjra.rdb.executer.usercollectuser.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectUserRowMapper implements RowMapper<UserCollectUser> {
    @Override
    public UserCollectUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCollectUser re = new UserCollectUser();
        re.setCollectId(rs.getLong("collect_id"));
        re.setUserId(rs.getLong("user_id"));
        re.setCollectUserId(rs.getLong("collect_user_id"));
        re.setCollectDate(rs.getTimestamp("collect_time"));
        return re;
    }
}
