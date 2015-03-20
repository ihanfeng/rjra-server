package com.hdg.rjra.rdb.executer.userscanuser.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.UserScanUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/20.
 */
public class UserScanUserRowMapper implements RowMapper<UserScanUser> {
    @Override
    public UserScanUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserScanUser re = new UserScanUser();
        re.setScanId(rs.getLong("scan_id"));
        re.setUserId(rs.getLong("user_id"));
        re.setScanUserId(rs.getLong("scan_user_id"));
        re.setScanTime(rs.getTimestamp("scan_time"));
        return re;
    }
}
