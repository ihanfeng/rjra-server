package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.domain.BaseDomain;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2015/3/23.
 */
public class UpdateByPk extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            BaseDomain baseDomain = (BaseDomain) params[0];
            SqlParam sqlParam = SqlUtils.buildUpdateSqlByDomain(baseDomain);
            if (sqlParam != null) {
                final String sql = sqlParam.getSql();
                final List<Object> objects = sqlParam.getObjects();
                getJdbcTemplate().update(new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(sql);
                        for (int i = 0; i < objects.size(); i++) {
                            ps.setObject(i + 1, objects.get(i));
                        }
                        return ps;
                    }
                });
                return Integer.valueOf(ResultType.SUCCESS.getCode());
            }
            else {
                return Integer.valueOf(ResultType.BUSINESS_ERROR.getCode());
            }
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }

    }
}
