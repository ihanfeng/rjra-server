package com.hdg.rjra.rdb.executer.user;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UpdateUserLocation extends AbstractExecuter {

    String sql = "UPDATE account_user SET user_login_longitude=?,user_login_latitude=?," +
            "user_update_time=? WHERE user_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long userId = (Long) params[0];
            final Double lng = (Double) params[1];
            final Double lat = (Double) params[2];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, lng);
                    ps.setObject(2, lat);
                    ps.setObject(3, new Date());
                    ps.setObject(4, userId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
