package com.hdg.rjra.rdb.executer.resume;

import com.hdg.rjra.base.utils.CoordinateUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.enumerate.ResumeMapping;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import com.hdg.rjra.rdb.proxy.utils.WhereAndSqlParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/29 0029.
 */
public class FindNearResumeByParamPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Map<ResumeMapping, Object> param = (Map<ResumeMapping, Object>) params[0];
        Double lng = (Double) params[1];
        Double lat = (Double) params[2];
        Integer raidus = (Integer) params[3];
        Integer[] status = (Integer[]) params[4];
        Integer firstResult = (Integer) params[5];
        Integer sizeNo = (Integer) params[6];
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        List<Object> objects = new ArrayList<Object>();
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select a.* from user_resume a left join account_user b on a.resume_id=b.resume_id where 1=1");
        WhereAndSqlParam whereAndSqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        if (whereAndSqlParam != null) {
            executeSql.append(whereAndSqlParam.getSql());
            objects.addAll(whereAndSqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and resume_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and user_login_longitude > ?");
        executeSql.append(" and user_login_longitude < ?");
        executeSql.append(" and user_login_latitude > ?");
        executeSql.append(" and user_login_latitude < ?");
        executeSql.append(" ORDER BY ACOS( SIN((? * 3.1415) / 180) * SIN((user_login_longitude * 3.1415) / 180) + COS((? * 3.1415) / 180) * COS((user_login_latitude * 3.1415) / 180) * COS((? * 3.1415) / 180 - (user_login_longitude * 3.1415) / 180)) * 6380 ");
        objects.add(doubles[0]);
        objects.add(doubles[1]);
        objects.add(doubles[2]);
        objects.add(doubles[3]);
        objects.add(lat);
        objects.add(lat);
        objects.add(lng);

        return findPager(executeSql.toString(), objects.toArray(), firstResult, sizeNo, rowMapper);
    }
}