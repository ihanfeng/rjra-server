package com.hdg.rjra.rdb.executer.usercollectuser;

import com.hdg.rjra.base.enumerate.UserCollectUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/20.
 */
public class DeleteUserCollectUser extends AbstractExecuter {

    String sql = "UPDATE user_collect_user SET " +
            "user_collect_user_status=? WHERE collect_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long collectId = (Long) params[0];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, UserCollectUserStatus.Delete.getCode());
                    ps.setObject(2, collectId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
