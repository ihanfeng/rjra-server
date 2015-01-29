package com.hdg.rjra.rdb.executer.resume.rowmapper;

import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class ResumeRowMapper implements RowMapper<Resume> {
    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume re = new Resume();
        re.setResumeId(rs.getLong("resume_id"));
        re.setCategoryLevel1Ids(StringUtils.stringToLongArray(rs.getString("category_level1_ids")));
        re.setCategoryLevel2Ids(StringUtils.stringToLongArray(rs.getString("category_level2_ids")));
        re.setCategoryLevel3Ids(StringUtils.stringToLongArray(rs.getString("category_level3_ids")));
        re.setResumeUserName(rs.getString("resume_user_name"));
        re.setResumeUserHeadImageFile(rs.getLong("resume_user_head_image_file"));
        re.setResumeGender(rs.getInt("resume_gender"));
        re.setResumeBirthday(rs.getTimestamp("resume_birthday"));
        re.setResumeExperience(rs.getLong("resume_experience"));
        re.setResumeWage(rs.getLong("resume_wage"));
        re.setResumeWorkStatus(rs.getInt("resume_work_status"));
        re.setResumeWantWorkAreaId(rs.getLong("resume_want_work_area_id"));
        re.setResumeWantWorkCityId(rs.getLong("resume_want_work_city_id"));
        re.setResumeWantWorkProvinceId(rs.getLong("resume_want_work_province_id"));
        re.setResumeMobile(rs.getString("resume_mobile"));
        re.setResumeQQ(rs.getString("resume_qq"));
        re.setResumeDesc(rs.getString("resume_desc"));
        re.setResumeStatus(rs.getInt("resume_status"));
        re.setResumeCreateTime(rs.getTimestamp("resume_create_time"));
        re.setResumeUpdateTime(rs.getTimestamp("resume_update_time"));
        re.setResumeRefreshTime(rs.getTimestamp("resume_refresh_time"));
        return re;
    }
}
