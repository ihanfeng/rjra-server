package com.hdg.rjra.rdb.serviceimpl.city.rowmapper;

import com.hdg.rjra.rdb.domain.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City re = new City();
        re.setCityId(rs.getLong("city_id"));
        re.setCityName(rs.getString("city_name"));
        re.setProvinceId(rs.getLong("province_id"));
        return re;
    }
}
