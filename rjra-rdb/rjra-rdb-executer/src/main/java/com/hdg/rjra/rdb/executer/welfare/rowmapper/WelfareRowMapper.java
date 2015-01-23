package com.hdg.rjra.rdb.executer.welfare.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Welfare;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class WelfareRowMapper implements RowMapper<Welfare> {
    @Override
    public Welfare mapRow(ResultSet rs, int rowNum) throws SQLException {
        Welfare re = new Welfare();
        re.setWelfareId(rs.getLong("welfare_id"));
        re.setWelfareInfo(rs.getString("welfare_info"));
        return re;
    }
}
