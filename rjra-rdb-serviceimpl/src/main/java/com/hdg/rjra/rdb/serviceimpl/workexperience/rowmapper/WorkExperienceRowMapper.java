package com.hdg.rjra.rdb.serviceimpl.workexperience.rowmapper;

import com.hdg.rjra.rdb.domain.WorkExperience;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class WorkExperienceRowMapper implements RowMapper<WorkExperience> {
    @Override
    public WorkExperience mapRow(ResultSet rs, int rowNum) throws SQLException {
        WorkExperience re = new WorkExperience();
        re.setWorkExperienceId(rs.getLong("experience_id"));
        re.setWorkExperienceInfo(rs.getString("experience_info"));
        return re;
    }
}
