package com.hdg.rjra.rdb.executer.resume;

import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/24 0024.
 */
public class CreateResume extends AbstractExecuter {

    String sql = "insert into user_resume(resume_mobile,resume_status, resume_create_time, resume_update_time, resume_refresh_time)" +
            " values (?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final String mobile = (String) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, mobile);
                ps.setObject(2, CompanyStatus.Active.getCode());
                ps.setObject(3, new Date());
                ps.setObject(4, new Date());
                ps.setObject(5, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
