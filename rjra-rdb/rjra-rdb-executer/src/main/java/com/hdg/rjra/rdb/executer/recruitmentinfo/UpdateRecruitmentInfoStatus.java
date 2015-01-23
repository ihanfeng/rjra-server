package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class UpdateRecruitmentInfoStatus extends AbstractExecuter {

    String sql = "UPDATE rec_info SET info_status=? WHERE info_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long infoId = (Long) params[0];
            final Integer status = (Integer) params[1];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, status);
                    ps.setObject(2, infoId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
