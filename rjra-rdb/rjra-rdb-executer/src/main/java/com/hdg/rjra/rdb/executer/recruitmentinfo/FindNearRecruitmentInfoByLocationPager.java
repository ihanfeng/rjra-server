package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.base.utils.CoordinateUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public class FindNearRecruitmentInfoByLocationPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Double lng = (Double) params[0];
        Double lat = (Double) params[1];
        Integer raidus = (Integer) params[2];
        Integer[] status = (Integer[]) params[3];
        Integer firstResult = (Integer) params[4];
        Integer sizeNo = (Integer) params[5];
        double[] doubles = CoordinateUtils.getAround(lat, lng, raidus);
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from rec_info where");
        executeSql.append(" info_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and info_work_longitude > ?");
        executeSql.append(" and info_work_longitude < ?");
        executeSql.append(" and info_work_latitude > ?");
        executeSql.append(" and info_work_latitude < ?");
        executeSql.append(" ORDER BY ACOS( SIN((? * 3.1415) / 180) * SIN((info_work_longitude * 3.1415) / 180) + COS((? * 3.1415) / 180) * COS((info_work_latitude * 3.1415) / 180) * COS((? * 3.1415) / 180 - (info_work_longitude * 3.1415) / 180)) * 6380 ");
        int count = 0;
        if (status != null) {
            count = status.length;
        }
        Object[] objects = new Object[count + 7];
        if (status != null) {

            for (int i = 0; i < status.length; i++) {
                objects[i] = status[i];
            }
        }
        objects[count] = doubles[0];
        objects[count + 1] = doubles[1];
        objects[count + 2] = doubles[2];
        objects[count + 3] = doubles[3];
        objects[count + 4] = lat;
        objects[count + 5] = lat;
        objects[count + 6] = lng;

        return findPager(executeSql.toString(), objects,firstResult,sizeNo, rowMapper);
    }
}
