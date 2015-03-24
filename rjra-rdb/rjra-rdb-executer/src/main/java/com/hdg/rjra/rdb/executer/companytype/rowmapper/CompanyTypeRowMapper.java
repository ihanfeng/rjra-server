package com.hdg.rjra.rdb.executer.companytype.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.CompanyType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/24.
 */
public class CompanyTypeRowMapper implements RowMapper<CompanyType> {
    @Override
    public CompanyType mapRow(ResultSet rs, int rowNum) throws SQLException {
        CompanyType re = new CompanyType();
        re.setCompanyTypeId(rs.getLong("company_type_id"));
        re.setCompanyTypeInfo(rs.getString("company_type_info"));
        return re;
    }
}