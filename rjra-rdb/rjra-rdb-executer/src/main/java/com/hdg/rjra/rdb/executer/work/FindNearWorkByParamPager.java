package com.hdg.rjra.rdb.executer.work;

import com.hdg.common.utils.CoordinateUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import com.hdg.rjra.rdb.proxy.utils.WhereAndSqlParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class FindNearWorkByParamPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Map<WorkMapping, Object> param = (Map<WorkMapping, Object>) params[0];
        Double lng = (Double) params[1];
        Double lat = (Double) params[2];
        Integer raidus = (Integer) params[3];
        Integer[] status = (Integer[]) params[4];
        Integer firstResult = (Integer) params[5];
        Integer sizeNo = (Integer) params[6];
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        List<Object> objects = new ArrayList<Object>();
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_work where 1=1");
        WhereAndSqlParam whereAndSqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        if (whereAndSqlParam != null) {
            executeSql.append(whereAndSqlParam.getSql());
            objects.addAll(whereAndSqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and work_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and work_longitude > ?");
        executeSql.append(" and work_longitude < ?");
        executeSql.append(" and work_latitude > ?");
        executeSql.append(" and work_latitude < ?");
        executeSql.append(" ORDER BY ACOS( SIN((? * 3.1415) / 180) * SIN((work_longitude * 3.1415) / 180) + COS((? * 3.1415) / 180) * COS((work_latitude * 3.1415) / 180) * COS((? * 3.1415) / 180 - (work_longitude * 3.1415) / 180)) * 6380 ");
        objects.add(doubles[0]);
        objects.add(doubles[1]);
        objects.add(doubles[2]);
        objects.add(doubles[3]);
        objects.add(lat);
        objects.add(lat);
        objects.add(lng);

        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, rowMapper);
    }
}