package com.hdg.rjra.rdb.executer.common.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/28.
 */
public class IdListRowMapper implements RowMapper<Long> {
    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Long(rs.getLong("id"));
    }
}
