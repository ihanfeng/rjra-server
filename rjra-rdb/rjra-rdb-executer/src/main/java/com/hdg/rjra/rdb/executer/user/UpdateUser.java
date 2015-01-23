package com.hdg.rjra.rdb.executer.user;

import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.domain.User;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UpdateUser extends AbstractExecuter {

    String sql = "UPDATE account_user SET user_name=?," +
            "user_head_image_file=?,user_email=?,user_province_id=?,user_city_id=?,user_area_id=?," +
            "user_address=?,user_work_status=?,user_hope_province_id=?,user_hope_city_id=?," +
            "user_hope_area_id=?,user_now_province_id=?,user_now_city_id=?,user_now_area_id=?," +
            "user_now_address=?,user_skills_level1=?,user_skills_level2=?,user_skills_level3=?," +
            "user_idcard=?,user_idcard_image_file=?,user_update_time=? WHERE user_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final User o = (User) params[0];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, o.getUserName());
                    if (o.getUserHeadImageFile() != null) {
                        ps.setObject(2, o.getUserHeadImageFile());
                    }
                    else {
                        ps.setObject(2, -1);
                    }
                    if (o.getUserEmail() != null) {
                        ps.setObject(3, o.getUserEmail());
                    }
                    else {
                        ps.setObject(3, "");
                    }
                    if (o.getUserProvinceId() != null) {
                        ps.setObject(4, o.getUserProvinceId());
                    }
                    else {
                        ps.setObject(4, -1);
                    }
                    if (o.getUserCityId() != null) {
                        ps.setObject(5, o.getUserCityId());
                    }
                    else {
                        ps.setObject(5, -1);
                    }
                    if (o.getUserAreaId() != null) {
                        ps.setObject(6, o.getUserAreaId());
                    }
                    else {
                        ps.setObject(6, -1);
                    }
                    if (o.getUserAddress() != null) {
                        ps.setObject(7, o.getUserAddress());
                    }
                    else {
                        ps.setObject(7, "");
                    }
                    ps.setObject(8, o.getUserWorkStatus());
                    if (o.getUserHopeProvinceId() != null) {
                        ps.setObject(9, o.getUserHopeProvinceId());
                    }
                    else {
                        ps.setObject(9, -1);
                    }
                    if (o.getUserHopeCityId() != null) {
                        ps.setObject(10, o.getUserHopeCityId());
                    }
                    else {
                        ps.setObject(10, -1);
                    }
                    if (o.getUserHopeAreaId() != null) {
                        ps.setObject(11, o.getUserHopeAreaId());
                    }
                    else {
                        ps.setObject(11, -1);
                    }
                    if (o.getUserNowProvinceId() != null) {
                        ps.setObject(12, o.getUserNowProvinceId());
                    }
                    else {
                        ps.setObject(12, -1);
                    }
                    if (o.getUserNowCityId() != null) {
                        ps.setObject(13, o.getUserNowCityId());
                    }
                    else {
                        ps.setObject(13, -1);
                    }
                    if (o.getUserNowAreaId() != null) {
                        ps.setObject(14, o.getUserNowAreaId());
                    }
                    else {
                        ps.setObject(14, -1);
                    }
                    if (o.getUserNowAddress() != null) {
                        ps.setObject(15, o.getUserNowAddress());
                    }
                    else {
                        ps.setObject(15, "");
                    }
                    if (o.getUserSkillsLevel1() != null) {
                        ps.setObject(16, StringUtils.longArrayToString(o.getUserSkillsLevel1()));
                    }
                    else {
                        ps.setObject(16, -1);
                    }
                    if (o.getUserSkillsLevel2() != null) {
                        ps.setObject(17, StringUtils.longArrayToString(o.getUserSkillsLevel2()));
                    }
                    else {
                        ps.setObject(17, -1);
                    }
                    if (o.getUserSkillsLevel3() != null) {
                        ps.setObject(18, StringUtils.longArrayToString(o.getUserSkillsLevel3()));
                    }
                    else {
                        ps.setObject(18, -1);
                    }
                    if (o.getUserIdcard() != null) {
                        ps.setObject(19, o.getUserIdcard());
                    }
                    else {
                        ps.setObject(19, "");
                    }
                    if (o.getUserIdcardImageFile() != null) {
                        ps.setObject(20, o.getUserIdcardImageFile());
                    }
                    else {
                        ps.setObject(20, -1);
                    }
                    ps.setObject(21, new Date());
                    ps.setObject(22, o.getUserId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
