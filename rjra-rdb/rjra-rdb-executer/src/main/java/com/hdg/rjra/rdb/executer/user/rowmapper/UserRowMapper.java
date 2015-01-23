package com.hdg.rjra.rdb.executer.user.rowmapper;

import com.hdg.rjra.base.utils.StringUtils;
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
        re.setUserMobile(rs.getString("user_mobile"));
        re.setUserPwd(rs.getString("user_pwd"));
        re.setUserName(rs.getString("user_name"));
        re.setUserHeadImageFile(rs.getLong("user_head_image_file"));
        re.setUserEmail(rs.getString("user_email"));
        re.setUserProvinceId(rs.getLong("user_province_id"));
        re.setUserCityId(rs.getLong("user_city_id"));
        re.setUserAreaId(rs.getLong("user_area_id"));
        re.setUserAddress(rs.getString("user_address"));
        re.setUserWorkStatus(rs.getInt("user_work_status"));
        re.setUserHopeProvinceId(rs.getLong("user_hope_province_id"));
        re.setUserHopeCityId(rs.getLong("user_hope_city_id"));
        re.setUserHopeAreaId(rs.getLong("user_hope_area_id"));
        re.setUserNowProvinceId(rs.getLong("user_now_province_id"));
        re.setUserNowCityId(rs.getLong("user_now_city_id"));
        re.setUserNowAreaId(rs.getLong("user_now_area_id"));
        re.setUserNowAddress(rs.getString("user_now_address"));
        String userSkillsLevel1Db = rs.getString("user_skills_level1");
        if (userSkillsLevel1Db != null) {
            re.setUserSkillsLevel1(StringUtils.stringToLongArray(userSkillsLevel1Db));
        }
        else{
            re.setUserSkillsLevel1(new Long[0]);
        }
        String userSkillsLevel2Db = rs.getString("user_skills_level2");
        if (userSkillsLevel2Db != null) {
            re.setUserSkillsLevel2(StringUtils.stringToLongArray(userSkillsLevel2Db));
        }
        else{
            re.setUserSkillsLevel2(new Long[0]);
        }
        String userSkillsLevel3Db = rs.getString("user_skills_level3");
        if (userSkillsLevel3Db != null) {
            re.setUserSkillsLevel3(StringUtils.stringToLongArray(userSkillsLevel3Db));
        }
        else{
            re.setUserSkillsLevel3(new Long[0]);
        }
        re.setUserIdcard(rs.getString("user_idcard"));
        re.setUserIdcardImageFile(rs.getLong("user_idcard_image_file"));
        re.setUserLoginLongitude(rs.getDouble("user_login_longitude"));
        re.setUserLoginLatitude(rs.getDouble("user_login_latitude"));
        re.setUserStatus(rs.getString("user_status"));
        re.setUserCreateTime(rs.getTimestamp("user_create_time"));
        re.setUserUpdateTime(rs.getTimestamp("user_update_time"));
        return re;
    }

}