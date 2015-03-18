package com.hdg.rjra.rdb.executer.usercollectwork.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectWorkRowMapper implements RowMapper<UserCollectWork> {
    @Override
    public UserCollectWork mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCollectWork re = new UserCollectWork();
        re.setCollectId(rs.getLong("collect_id"));
        re.setUserId(rs.getLong("user_id"));
        re.setWorkId(rs.getLong("work_id"));
        re.setCollectDate(rs.getTimestamp("collect_time"));
        return re;
    }
}
