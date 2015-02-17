package com.hdg.rjra.rdb.serviceimpl.area.rowmapper;

import com.hdg.rjra.rdb.domain.Area;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class AreaRowMapper implements RowMapper<Area> {
    @Override
    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area re = new Area();
        re.setAreaId(rs.getLong("area_id"));
        re.setAreaName(rs.getString("area_name"));
        re.setCityId(rs.getLong("city_id"));
        return re;
    }
}
