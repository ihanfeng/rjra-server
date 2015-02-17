package com.hdg.rjra.rdb.serviceimpl.company.rowmapper;

import com.hdg.rjra.rdb.domain.Company;
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
        re.setCompanyName(rs.getString("company_name"));
        re.setCompanyAreaId(rs.getLong("company_area_id"));
        re.setCompanyCityId(rs.getLong("company_city_id"));
        re.setCompanyProvinceId(rs.getLong("company_province_id"));
        re.setCompanyAddress(rs.getString("company_address"));
        re.setCompanyContact(rs.getString("company_contact"));
        re.setCompanyContactMobile(rs.getString("company_contact_mobile"));
        re.setCompanyLogoImageFile(rs.getLong("company_logo_image_file"));
        re.setCompanyBizlicenseImageFile(rs.getLong("company_bizlicense_image_file"));
        re.setCompanyFacadeImageFile(rs.getLong("company_facade_image_file"));
        re.setCompanyUserIdCardImageFile(rs.getLong("company_user_idcard_image_file"));
        re.setCompanyStatus(rs.getInt("company_status"));
        re.setCompanyCreateTime(rs.getTimestamp("company_create_time"));
        re.setCompanyUpdateTime(rs.getTimestamp("company_update_time"));
        re.setCompanyChangeTime(rs.getTimestamp("company_change_time"));
        re.setCompanyDesc(rs.getString("company_desc"));;
        re.setCompanyExamineStatus(rs.getInt("company_examine_status"));
        re.setCompanyExamineTime(rs.getTimestamp("company_examine_time"));
        re.setCompanyExamineResult(rs.getString("company_examine_result"));
        return re;
    }
}