package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UpdateCompanyBizlicense extends AbstractExecuter {

    String sql = "UPDATE account_company SET " +
            "company_bizlicense_image_file=? WHERE company_id =?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Long companyId = (Long) params[0];
            final Long bizlicenseFile = (Long) params[1];
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, bizlicenseFile);
                    ps.setObject(2, companyId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
