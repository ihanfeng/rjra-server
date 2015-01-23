package com.hdg.rjra.rdb.executer.employ;

import com.hdg.rjra.base.enumerate.EmployStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class UpdateEmployEntryPlanTime extends AbstractExecuter {
    String sql = "UPDATE rec_employ SET employ_entry_plan_time=?,employ_status=?  WHERE employ_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long employId = (Long) params[0];
            final Date planTime = (Date) params[1];
            getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, planTime);
                    ps.setObject(2, EmployStatus.Entry.getCode());
                    ps.setObject(3, employId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        } else {
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
