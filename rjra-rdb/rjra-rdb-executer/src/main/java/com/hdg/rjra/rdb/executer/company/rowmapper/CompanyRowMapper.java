package com.hdg.rjra.rdb.executer.company.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyRowMapper implements RowMapper<Company> {
    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company re = new Company();
        re.setCompanyId(rs.getLong("company_id"));
        re.setCompanyEmail(rs.getString("company_email"));
        re.setCompanyMobile(rs.getString("company_mobile"));
        re.setCompanyName(rs.getString("company_name"));
        re.setCompanyPwd(rs.getString("company_pwd"));
        re.setCompanyLogoImageFile(rs.getLong("company_logo_image_file"));
        re.setCompanyAreaId(rs.getLong("company_area_id"));
        re.setCompanyCityId(rs.getLong("company_city_id"));
        re.setCompanyProvinceId(rs.getLong("company_province_id"));
        re.setCompanyAddress(rs.getString("company_address"));
        re.setCompanyContact(rs.getString("company_contact"));
        re.setCompanyContactMobile(rs.getString("company_contact_mobile"));
        re.setCompanyFixedPhone(rs.getString("company_fixed_phone"));
        re.setCompanyBizlicenseNumber(rs.getString("company_bizlicense_number"));
        re.setCompanyBizlicenseImageFile(rs.getLong("company_bizlicense_image_file"));
        re.setExamineTime(rs.getTimestamp("examine_time"));
        re.setExamineStatus(rs.getInt("examine_status"));
        re.setExamineResultInfo(rs.getString("examine_result_info"));
        re.setCompanyStatus(rs.getInt("company_status"));
        re.setCompanyCreateTime(rs.getTimestamp("company_create_time"));
        re.setCompanyUpdateTime(rs.getTimestamp("company_update_time"));
        re.setCompanyDesc(rs.getString("company_desc"));
        return re;
    }
}