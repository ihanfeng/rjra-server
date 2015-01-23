package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.base.enumerate.ExamineStatus;
import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.domain.RecruitmentInfo;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class UpdateRecruitmentInfo extends AbstractExecuter {

    String sql = "UPDATE rec_info SET " +
            "company_id=?,category_level1_id=?,category_level2_id=?,category_level3_id=?,area_id=?," +
            "city_id=?,province_id=?,wage_id=?,experience_id=?,education_id=?,info_welfare=?,info_type=?," +
            "info_image_files=?,info_vedio_file=?,info_vedio_content=?,info_work_address=?,info_need_person=?," +
            "info_upper_age_demand=?,info_lower_age_demand=?,info_work_longitude=?,info_work_latitude=?," +
            "info_status=?,info_create_time=?,info_update_time=?,info_desc=? WHERE info_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final RecruitmentInfo o = (RecruitmentInfo) params[0];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, o.getCompanyId());
                    ps.setObject(2, o.getCategoryLevel1Id());
                    if (o.getCategoryLevel2Id() != null) {
                        ps.setObject(3, o.getCategoryLevel2Id());
                    }
                    else{
                        ps.setObject(3, -1);
                    }
                    if (o.getCategoryLevel3Id() != null) {
                        ps.setObject(4, o.getCategoryLevel3Id());
                    }
                    else{
                        ps.setObject(4, -1);
                    }
                    ps.setObject(5, o.getAreaId());
                    ps.setObject(6, o.getCityId());
                    ps.setObject(7, o.getProvinceId());
                    ps.setObject(8, o.getWageId());
                    ps.setObject(9, o.getExperienceId());
                    if (o.getEducationId() != null) {
                        ps.setObject(10, o.getEducationId());
                    }
                    else{
                        ps.setObject(10, -1);
                    }
                    if (o.getInfoWelfare() != null) {
                        ps.setObject(11, StringUtils.longArrayToString(o.getInfoWelfare()));
                    }else{
                        ps.setObject(11,"");
                    }
                    ps.setObject(12, o.getInfoType());
                    if (o.getInfoImageFiles() != null) {
                        ps.setObject(13, StringUtils.longArrayToString(o.getInfoImageFiles()));
                    }else{
                        ps.setObject(13, "");
                    }
                    if (o.getInfoVedioFile() != null) {
                        ps.setObject(14, o.getInfoVedioFile());
                    }
                    else {
                        ps.setObject(14, "");
                    }
                    if (o.getInfoVedioContent() != null) {
                        ps.setObject(15, o.getInfoVedioContent());
                    }
                    else {
                        ps.setObject(15, "");
                    }
                    if (o.getInfoWorkAddress() != null) {
                        ps.setObject(16, o.getInfoWorkAddress());
                    }
                    else {
                        ps.setObject(16, "");
                    }
                    if (o.getInfoNeedPerson() != null) {
                        ps.setObject(17, o.getInfoNeedPerson());
                    }
                    else {
                        ps.setObject(17, 0);
                    }
                    if (o.getInfoUpperAgeDemand() != null) {
                        ps.setObject(18, o.getInfoUpperAgeDemand());
                    }
                    else {
                        ps.setObject(18, 0);
                    }
                    if (o.getInfoLowerAgeDemand() != null) {
                        ps.setObject(19, o.getInfoLowerAgeDemand());
                    }
                    else {
                        ps.setObject(19, 0);
                    }
                    ps.setObject(20, o.getInfoWorkLongitude());
                    ps.setObject(21, o.getInfoWorkLatitude());
                    ps.setObject(22, o.getInfoStatus());
                    if (o.getInfoCreateTime() != null) {
                        ps.setObject(23, o.getInfoCreateTime());
                    }
                    else {
                        ps.setObject(23, new Date());
                    }
                    if (o.getInfoUpdateTime() != null) {
                        ps.setObject(24, o.getInfoUpdateTime());
                    }
                    else {
                        ps.setObject(24, new Date());
                    }
                    ps.setObject(25, o.getInfoDesc());
                    ps.setObject(26, o.getInfoId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
