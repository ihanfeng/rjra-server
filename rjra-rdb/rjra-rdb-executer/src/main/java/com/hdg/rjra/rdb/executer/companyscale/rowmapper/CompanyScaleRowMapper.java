package com.hdg.rjra.rdb.executer.companyscale.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.CompanyScale;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/24.
 */
public class CompanyScaleRowMapper implements RowMapper<CompanyScale> {
    @Override
    public CompanyScale mapRow(ResultSet rs, int rowNum) throws SQLException {
        CompanyScale re = new CompanyScale();
        re.setCompanyScaleId(rs.getLong("company_scale_id"));
        re.setCompanyScaleInfo(rs.getString("company_scale_info"));
        return re;
    }
}