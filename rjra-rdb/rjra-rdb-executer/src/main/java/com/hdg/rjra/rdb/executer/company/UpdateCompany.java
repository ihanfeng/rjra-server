package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.domain.Company;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UpdateCompany extends AbstractExecuter {

    String sql = "UPDATE account_company SET " +
            "company_email=?,company_name=?,company_area_id=?,company_city_id=?,company_province_id=?," +
            "company_address=?,company_contact=?,company_contact_mobile=?," +
            "company_fixed_phone=?,company_bizlicense_number=?," +
            "company_update_time=?,company_desc=? WHERE company_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Company o = (Company) params[0];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    if (o.getCompanyEmail() != null) {
                        ps.setObject(1, o.getCompanyEmail());
                    }
                    else {
                        ps.setObject(1, "");
                    }
                    ps.setObject(2, o.getCompanyName());
                    ps.setObject(3, o.getCompanyAreaId());
                    ps.setObject(4, o.getCompanyCityId());
                    ps.setObject(5, o.getCompanyProvinceId());
                    ps.setObject(6, o.getCompanyAddress());
                    ps.setObject(7, o.getCompanyContact());
                    ps.setObject(8, o.getCompanyContactMobile());
                    if (o.getCompanyFixedPhone() != null) {
                        ps.setObject(9, o.getCompanyFixedPhone());
                    }
                    else {
                        ps.setObject(9, "");
                    }
                    if (o.getCompanyBizlicenseNumber() != null) {
                        ps.setObject(10, o.getCompanyBizlicenseNumber());
                    }
                    else {
                        ps.setObject(10, "");
                    }
                    ps.setObject(11, new Date());
                    if (o.getCompanyDesc() != null) {
                        ps.setObject(12, o.getCompanyDesc());
                    }
                    else {
                        ps.setObject(12, -1);
                    }
                    ps.setObject(13, o.getCompanyId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
