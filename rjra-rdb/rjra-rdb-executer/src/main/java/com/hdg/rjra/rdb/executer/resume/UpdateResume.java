package com.hdg.rjra.rdb.executer.resume;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/24 0024.
 */
public class UpdateResume extends AbstractExecuter {

    String sql = "UPDATE user_resume SET " +
            "category_leve1_id=?,category_leve2_id=?,category_leve3_id=?,resume_user_name=?," +
            "resume_gender=?,resume_birthday=?,resume_experience=?," +
            "resume_work_status=?,resume_want_work_area=?,resume_want_work_city=?,resume_want_work_province=?," +
            "resume_qq=?,resume_desc=?,resume_update_time=? WHERE resume_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Resume resume = (Resume) params[0];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, resume.getCategoryLevel1Id());
                    if (resume.getCategoryLevel2Id() != null) {
                        ps.setObject(2, resume.getCategoryLevel2Id());
                    } else {
                        ps.setObject(2, -1);
                    }
                    if (resume.getCategoryLevel3Id() != null) {
                        ps.setObject(3, resume.getCategoryLevel3Id());
                    } else {
                        ps.setObject(3, -1);
                    }
                    ps.setObject(4, resume.getResumeUserName());
                    ps.setObject(5, resume.getResumeGender());
                    ps.setObject(6, resume.getResumeBirthday());
                    ps.setObject(7, resume.getResumeExperience());
                    ps.setObject(8, resume.getResumeWorkStatus());
                    ps.setObject(9, resume.getResumeWantWorkAreaId());
                    ps.setObject(10, resume.getResumeWantWorkCityId());
                    ps.setObject(11, resume.getResumeWantWorkProvinceId());
                    if (resume.getResumeQQ() != null) {
                        ps.setObject(12, resume.getResumeQQ());
                    } else {
                        ps.setObject(12, "");
                    }
                    ps.setObject(13, resume.getResumeDesc());
                    ps.setObject(14, new Date());
                    ps.setObject(15, resume.getResumeId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
