package com.hdg.rjra.rdb.executer.user.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User re = new User();
        re.setUserId(rs.getLong("user_id"));
        re.setResumeId(rs.getLong("resume_id"));
        re.setCompanyId(rs.getLong("company_id"));
        re.setUserMobile(rs.getString("user_mobile"));
        re.setUserNickName(rs.getString("user_nickname"));
        re.setUserHeadImageFile(rs.getLong("user_head_image_file"));
        re.setUserLoginLongitude(rs.getDouble("user_login_longitude"));
        re.setUserLoginLatitude(rs.getDouble("user_login_latitude"));
        re.setUserGender(rs.getInt("user_gender"));
        re.setUserStatus(rs.getInt("user_status"));
        re.setUserCreateTime(rs.getTimestamp("user_create_time"));
        re.setUserUpdateTime(rs.getTimestamp("user_update_time"));
        re.setUserLoginType(rs.getInt("user_login_type"));
        return re;
    }

}