package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.base.enumerate.ExamineStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class SaveCompany extends AbstractExecuter {

    String sql = "insert into account_company(company_mobile, company_pwd, company_status," +
            " company_create_time, company_update_time, examine_status)" +
            " values (?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final String mobile = (String) params[0];
        final String pwd = (String) params[1];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, mobile);
                ps.setObject(2, pwd);
                ps.setObject(3, CompanyStatus.Active.getCode());
                ps.setObject(4, new Date());
                ps.setObject(5, new Date());
                ps.setObject(6, ExamineStatus.Pending.getCode());
            }
        };
        return saveResultId(sql, pst);
    }
}
