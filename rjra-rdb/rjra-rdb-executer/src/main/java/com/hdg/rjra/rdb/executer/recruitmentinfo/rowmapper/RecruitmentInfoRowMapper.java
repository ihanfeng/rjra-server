package com.hdg.rjra.rdb.executer.recruitmentinfo.rowmapper;

import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.proxy.domain.RecruitmentInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class RecruitmentInfoRowMapper implements RowMapper<RecruitmentInfo> {
    @Override
    public RecruitmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecruitmentInfo re = new RecruitmentInfo();
        re.setInfoId(rs.getLong("info_id"));
        re.setCompanyId(rs.getLong("company_id"));
        re.setCategoryLevel1Id(rs.getLong("category_level1_id"));
        re.setCategoryLevel2Id(rs.getLong("category_level2_id"));
        re.setCategoryLevel3Id(rs.getLong("category_level3_id"));
        re.setAreaId(rs.getLong("area_id"));
        re.setCityId(rs.getLong("city_id"));
        re.setProvinceId(rs.getLong("province_id"));
        re.setWageId(rs.getLong("wage_id"));
        re.setExperienceId(rs.getLong("experience_id"));
        re.setEducationId(rs.getLong("education_id"));
        String infoWelfare = rs.getString("info_welfare");
        if (infoWelfare != null) {
            re.setInfoWelfare(StringUtils.stringToLongArray(infoWelfare));
        }
        else{
            re.setInfoWelfare(new Long[0]);
        }
        re.setInfoType(rs.getInt("info_type"));
        String infoImageFiles = rs.getString("info_image_files");
        if (infoWelfare != null) {
            re.setInfoImageFiles(StringUtils.stringToLongArray(infoImageFiles));
        }
        else{
            re.setInfoImageFiles(new Long[0]);
        }
        String infoVedioFile = rs.getString("info_vedio_file");
        if(infoVedioFile == null || StringUtils.isEmpty(infoVedioFile)){
            re.setInfoVedioFile(null);
        }
        else{
            re.setInfoVedioFile(Long.valueOf(infoVedioFile));
        }
        re.setInfoVedioContent(rs.getString("info_vedio_content"));
        re.setInfoWorkAddress(rs.getString("info_work_address"));
        re.setInfoNeedPerson(rs.getInt("info_need_person"));
        re.setInfoUpperAgeDemand(rs.getInt("info_upper_age_demand"));
        re.setInfoLowerAgeDemand(rs.getInt("info_lower_age_demand"));
        re.setInfoWorkLongitude(rs.getDouble("info_work_longitude"));
        re.setInfoWorkLatitude(rs.getDouble("info_work_latitude"));
        re.setInfoStatus(rs.getInt("info_status"));
        re.setInfoCreateTime(rs.getTimestamp("info_create_time"));
        re.setInfoUpdateTime(rs.getTimestamp("info_update_time"));
        re.setInfoDesc(rs.getString("info_desc"));
        re.setExamineTime(rs.getTimestamp("examine_time"));
        re.setExamineStatus(rs.getInt("examine_status"));
        re.setExamineResultInfo(rs.getString("examine_result_info"));
        return re;
    }
}
