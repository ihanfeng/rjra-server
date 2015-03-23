package com.hdg.rjra.rdb.executer.common;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.BaseDomain;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class UpdateByPk extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        BaseDomain baseDomain = (BaseDomain) params[0];
        SqlParam sqlParam = SqlUtils.buildUpdateSqlByDomain(baseDomain);
        return getJdbcTemplate().update(sqlParam.getSql(), sqlParam.getObjects());
    }
}
