package com.hdg.rjra.rdb.executer.userapplywork.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserApplyWorkRowMapper implements RowMapper<UserApplyWork> {
    @Override
    public UserApplyWork mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserApplyWork re = new UserApplyWork();
        re.setApplyId(rs.getLong("apply_id"));
        re.setUserId(rs.getLong("user_id"));
        re.setWorkId(rs.getLong("work_id"));
        re.setApplyTime(rs.getTimestamp("apply_time"));
        re.setUserApplyWorkStatus(rs.getInt("user_apply_work_status"));
        return re;
    }
}
