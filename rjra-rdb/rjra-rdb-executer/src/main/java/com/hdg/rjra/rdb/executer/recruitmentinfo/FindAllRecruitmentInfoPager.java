package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/9 0009.
 */
public class FindAllRecruitmentInfoPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Integer[] status = (Integer[]) params[0];
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from rec_info where info_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by info_create_time desc");
        return findPager(executeSql.toString(), status,firstResult,sizeNo, rowMapper);
    }
}
