package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserApplyWorkByWorkIds extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Long[] workIds = (Long[]) params[0];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_apply_work where 1=1");
        executeSql.append(" and work_id in (");
        executeSql.append(SqlUtils.appendPlaceholder(workIds.length));
        executeSql.append(")");
        executeSql.append(" and and user_apply_work_status = ");
        executeSql.append(UserApplyWorkStatus.Active.getCode());
        return  getJdbcTemplate().query(executeSql.toString(), params, rowMapper);
    }
}
