package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.base.enumerate.ExamineStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.BatchPstAssign;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.Company;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class BatchSaveCompany extends AbstractExecuter {

    String sql = "insert into `user_company`(`company_name`,`company_type`," +
            "`company_scale`,`company_address`,`company_contact`,`company_contact_mobile`,`company_email`,`company_status`,`company_create_time`," +
            "`company_update_time`,`company_desc`,`company_change_time`," +
            "`company_examine_status`,`company_tag`,`company_data_type`) " +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    @Override
    public Object execute(Object[] params) {
        final List<Company> companyList = (List<Company>) params[0];

        return batchSaveResultId(sql, new BatchPstAssign<Company>() {
            @Override
            public void setParam(PreparedStatement ps, Company company) throws SQLException {
                Date now = new Date();
                ps.setObject(1, company.getCompanyName());
                ps.setObject(2, company.getCompanyType());
                ps.setObject(3, company.getCompanyScale());
                ps.setObject(4, company.getCompanyAddress());
                ps.setObject(5, company.getCompanyContact());
                ps.setObject(6, company.getCompanyContactMobile());
                ps.setObject(7, company.getCompanyEmail());
                ps.setObject(8, CompanyStatus.Active.getCode());
                ps.setObject(9, now);
                ps.setObject(10, now);
                ps.setObject(11, company.getCompanyDesc());
                ps.setObject(12, now);
                ps.setObject(13, ExamineStatus.NotReviewed.getCode());
                ps.setObject(14, company.getCompanyTag());
                ps.setObject(15, company.getCompanyDataType());
            }
        }, companyList);
    }
}
