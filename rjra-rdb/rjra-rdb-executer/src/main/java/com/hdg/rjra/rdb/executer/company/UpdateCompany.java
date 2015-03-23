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

    String sql = "UPDATE user_company SET " +
            "company_name=?,company_type=?,company_scale=?,company_province_id=?,company_city_id=?,company_area_id=?,company_address=?," +
            "company_contact=?,company_contact_mobile=?,company_email=?,company_update_time=?,company_desc=? WHERE company_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Company o = (Company) params[0];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, o.getCompanyName());
                    ps.setObject(2, o.getCompanyType());
                    ps.setObject(3, o.getCompanyScale());
                    ps.setObject(4, o.getCompanyProvinceId());
                    ps.setObject(5, o.getCompanyCityId());
                    ps.setObject(6, o.getCompanyAreaId());
                    ps.setObject(7, o.getCompanyAddress());
                    ps.setObject(8, o.getCompanyContact());
                    ps.setObject(9, o.getCompanyEmail());
                    ps.setObject(9, o.getCompanyContactMobile());
                    ps.setObject(10, new Date());
                    ps.setObject(11, o.getCompanyDesc());
                    ps.setObject(12, o.getCompanyId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
