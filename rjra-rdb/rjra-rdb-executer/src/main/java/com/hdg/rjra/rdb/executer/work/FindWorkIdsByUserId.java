package com.hdg.rjra.rdb.executer.work;

import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindWorkIdsByUserId extends AbstractExecuter {
    static String sql = "select work_id from user_work where user_id = ? and work_status="+ WorkStatus.Active;
    @Override
    public Object execute(Object[] params) {
        List list = getJdbcTemplate().queryForList(sql, params, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("work_id");
            }
        });
        return list;
    }
}