package com.hdg.rjra.rdb.serviceimpl.education.rowmapper;

import com.hdg.rjra.rdb.domain.Education;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class EducationRowMapper implements RowMapper<Education> {
    @Override
    public Education mapRow(ResultSet rs, int rowNum) throws SQLException {
        Education re = new Education();
        re.setEducationId(rs.getLong("education_id"));
        re.setEducationInfo(rs.getString("education_info"));
        return re;
    }
}
