package com.hdg.rjra.rdb.executer.user;

import com.hdg.common.utils.CoordinateUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.enumerate.UserMapping;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import com.hdg.rjra.rdb.proxy.utils.WhereAndSqlParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public class FindNearUserPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Double lng = (Double) params[0];
        Double lat = (Double) params[1];
        Integer raidus = (Integer) params[2];
        Integer[] status = (Integer[]) params[3];
        Integer firstResult = (Integer) params[4];
        Integer sizeNo = (Integer) params[5];
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        List<Object> objects = new ArrayList<Object>();
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from account_user where 1=1");
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and user_status in (");
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
        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, rowMapper);
    }
}