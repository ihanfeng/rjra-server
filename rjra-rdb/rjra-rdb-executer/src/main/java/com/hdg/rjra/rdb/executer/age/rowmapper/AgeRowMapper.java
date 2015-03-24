package com.hdg.rjra.rdb.executer.age.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Age;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/24.
 */
public class AgeRowMapper implements RowMapper<Age> {
    @Override
    public Age mapRow(ResultSet rs, int rowNum) throws SQLException {
        Age re = new Age();
        re.setAgeId(rs.getLong("age_id"));
        re.setAgeInfo(rs.getString("age_info"));
        return re;
    }
}
