package com.hdg.rjra.rdb.executer.user;

import com.hdg.rjra.base.enumerate.UserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class SaveUser extends AbstractExecuter {

    static String sql = "insert into account_user(resume_id,company_id,user_mobile, user_pwd, user_status, user_create_time, user_update_time) values (?,?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final Long resumeId = (Long) params[0];
        final Long companyId = (Long) params[1];
        final String mobile = (String) params[2];
        final String pwd = (String) params[3];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, resumeId);
                ps.setObject(2, companyId);
                ps.setObject(3, mobile);
                ps.setObject(4, pwd);
                ps.setObject(5, UserStatus.Active.getCode());
                ps.setObject(6, new Date());
                ps.setObject(7, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
