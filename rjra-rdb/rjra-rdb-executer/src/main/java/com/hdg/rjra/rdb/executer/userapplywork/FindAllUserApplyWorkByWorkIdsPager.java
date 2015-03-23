package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserApplyWorkByWorkIdsPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        List<Long> workIds = (List<Long>) params[0];
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];;
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_apply_work where 1=1");
        executeSql.append(" and work_id in (");
        executeSql.append(SqlUtils.appendPlaceholder(workIds.size()));
        executeSql.append(")");
        executeSql.append(" and and user_apply_work_status = ");
        executeSql.append(UserApplyWorkStatus.Active.getCode());
        List<Object> objects = new ArrayList<Object>();
        objects.addAll(workIds);
        return findPager(executeSql.toString(), objects.toArray(), firstResult, sizeNo, rowMapper);
    }
}
