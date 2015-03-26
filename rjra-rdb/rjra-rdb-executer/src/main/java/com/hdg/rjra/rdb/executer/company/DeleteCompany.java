package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/3/25.
 */
public class DeleteCompany extends AbstractExecuter {
    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final List<Long> list = (List<Long>) params[0];
            StringBuilder sql = new StringBuilder();
            sql.append(" delete from user_company where company_id in ( ");
            sql.append(SqlUtils.appendPlaceholder(list.size()));
            sql.append(" )");
            getJdbcTemplate().update(sql.toString(), list.toArray());
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}