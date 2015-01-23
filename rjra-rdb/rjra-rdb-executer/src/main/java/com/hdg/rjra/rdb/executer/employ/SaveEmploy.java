package com.hdg.rjra.rdb.executer.employ;

import com.hdg.rjra.base.enumerate.*;
import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.Employ;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class SaveEmploy extends AbstractExecuter {

    static String sql = "insert  into `rec_employ`(info_id,company_id,user_id,employ_type," +
            "employ_time,employ_status, employ_company_status,employ_user_status) values (?,?,?,?,?,?,?,?);";

    @Override
    public Object execute(Object[] params) {
        final Employ o = (Employ) params[0];

        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, o.getInfoId());
                ps.setObject(2, o.getCompanyId());
                ps.setObject(3, o.getUserId());
                ps.setObject(4, o.getEmployType());
                ps.setObject(5, new Date());
                ps.setObject(6, EmployStatus.Apply.getCode());
                ps.setObject(7, EmployCompanyStatus.Active.getCode());
                ps.setObject(8, EmployUserStatus.Active.getCode());
            }
        };
        return saveResultId(sql, pst);
    }
}
