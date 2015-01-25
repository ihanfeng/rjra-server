package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CreateCompany extends AbstractExecuter {

    String sql = "insert into user_company(company_status, company_create_time, company_update_time)" +
            " values (?,?,?)";

    @Override
    public Object execute(Object[] params) {
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, CompanyStatus.Active.getCode());
                ps.setObject(2, new Date());
                ps.setObject(3, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
