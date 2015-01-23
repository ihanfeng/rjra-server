package com.hdg.rjra.rdb.executer.user;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class UpdateUserIdcard extends AbstractExecuter {

    String sql = "UPDATE account_user SET " +
            "user_idcard_image_file=? WHERE user_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long userId = (Long) params[0];
            final Long fileId = (Long) params[1];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, userId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
