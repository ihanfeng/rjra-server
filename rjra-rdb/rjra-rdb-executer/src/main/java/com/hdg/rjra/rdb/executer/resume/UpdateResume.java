package com.hdg.rjra.rdb.executer.resume;

import com.hdg.rjra.base.utils.StringUtils;
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
            "category_leve1_ids=?,category_leve2_ids=?,category_leve3_ids=?,resume_user_name=?," +
            "resume_gender=?,resume_birthday=?,resume_experience=?," +
            "resume_work_status=?,resume_want_work_area_id=?,resume_want_work_city_id=?,resume_want_work_province_id=?," +
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
                    ps.setObject(1, StringUtils.longArrayToString(resume.getCategoryLevel1Ids()));
                    ps.setObject(2, StringUtils.longArrayToString(resume.getCategoryLevel2Ids()));
                    ps.setObject(3, StringUtils.longArrayToString(resume.getCategoryLevel3Ids()));
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
