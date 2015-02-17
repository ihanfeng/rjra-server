package com.hdg.rjra.rdb.serviceimpl.wage.rowmapper;

import com.hdg.rjra.rdb.domain.Wage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class WageRowMapper implements RowMapper<Wage> {
    @Override
    public Wage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wage re = new Wage();
        re.setWageId(rs.getLong("wage_id"));
        re.setWageInfo(rs.getString("wage_info"));
        return re;
    }
}
