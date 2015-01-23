package com.hdg.rjra.rdb.executer.province.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Province;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class ProvinceRowMapper implements RowMapper<Province> {
    @Override
    public Province mapRow(ResultSet rs, int rowNum) throws SQLException {
        Province re = new Province();
        re.setProvinceId(rs.getLong("province_id"));
        re.setProvinceName(rs.getString("province_name"));
        return re;
    }
}
