package com.hdg.rjra.rdb.executer.work;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class FindAllWorkByParamPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Map<WorkMapping, Object> param = (Map<WorkMapping, Object>) params[0];
        Integer[] status = (Integer[]) params[1];
        Integer firstResult = (Integer) params[2];
        Integer sizeNo = (Integer) params[3];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_work where 1=1");
        SqlParam sqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        List<Object> objects = new ArrayList<Object>();
        if (sqlParam != null) {
            executeSql.append(sqlParam.getSql());
            objects.addAll(sqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and work_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by work_create_time desc");

        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, rowMapper);
    }
}