package com.hdg.rjra.rdb.executer.employ;

import com.hdg.rjra.base.enumerate.EmployStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class UpdateEmployResult extends AbstractExecuter {


    String sql = "UPDATE rec_employ SET employ_result_status=?,employ_result_info=?,employ_status=? WHERE employ_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long employId = (Long) params[0];
            final Integer employResultStatus = (Integer) params[1];
            final String employResultInfo = (String) params[2];
            getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, employResultStatus);
                    ps.setObject(2, employResultInfo);
                    ps.setObject(3, EmployStatus.Result.getCode());
                    ps.setObject(4, employId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        } else {
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
