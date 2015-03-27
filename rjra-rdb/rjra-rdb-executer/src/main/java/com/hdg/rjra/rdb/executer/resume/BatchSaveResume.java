package com.hdg.rjra.rdb.executer.resume;

import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.base.enumerate.ExamineStatus;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.BatchPstAssign;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.Resume;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class BatchSaveResume extends AbstractExecuter {

    String sql = "insert into user_resume(category_level1_ids,category_level2_ids," +
            "category_level3_ids,resume_user_name,resume_gender," +
            "resume_birthday,resume_wage,resume_experience,resume_work_status,resume_want_work_area_id," +
            "resume_want_work_city_id,resume_want_work_province_id,resume_home_area_id,resume_home_city_id," +
            "resume_home_province_id,resume_home_address,resume_mobile,resume_qq,resume_desc,resume_status," +
            "resume_create_time,resume_update_time,resume_refresh_time,resume_longitude,resume_latitude,age_id,resume_tag,resume_data_type) values " +
            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    @Override
    public Object execute(Object[] params) {
        final List<Resume> resumeList = (List<Resume>) params[0];

        return batchSaveResultId(sql, new BatchPstAssign<Resume>() {
            @Override
            public void setParam(PreparedStatement ps, Resume resume) throws SQLException {
                Date now = new Date();
                ps.setObject(1, StringUtils.longArrayToString(resume.getCategoryLevel1Ids()));
                ps.setObject(2, StringUtils.longArrayToString(resume.getCategoryLevel2Ids()));
                ps.setObject(3, StringUtils.longArrayToString(resume.getCategoryLevel3Ids()));
                ps.setObject(4, resume.getResumeUserName());
                ps.setObject(5, resume.getResumeGender());
                ps.setObject(6, resume.getResumeBirthday());
                ps.setObject(7, resume.getResumeWage());
                ps.setObject(8,  resume.getResumeExperience());
                ps.setObject(9,  resume.getResumeWorkStatus());
                ps.setObject(10, resume.getResumeWantWorkAreaId());
                ps.setObject(11, resume.getResumeWantWorkCityId());
                ps.setObject(12, resume.getResumeWantWorkProvinceId());
                ps.setObject(13, resume.getResumeHomeAreaId());
                ps.setObject(14, resume.getResumeHomeCityId());
                ps.setObject(15, resume.getResumeHomeProvinceId());
                ps.setObject(16, resume.getResumeHomeAddress());
                ps.setObject(17, resume.getResumeMobile());
                ps.setObject(18, resume.getResumeQQ());
                ps.setObject(19, resume.getResumeDesc());
                ps.setObject(20, ResumeStatus.Active.getCode());
                ps.setObject(21, now);
                ps.setObject(22, now);
                ps.setObject(23, now);
                ps.setObject(24, resume.getResumeLongitude());
                ps.setObject(25, resume.getResumeLatitude());
                ps.setObject(26, resume.getAgeId());
                ps.setObject(27, resume.getResumeTag());
                ps.setObject(28, resume.getResumeDataType());
            }
        }, resumeList);
    }
}
