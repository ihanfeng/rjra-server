package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/9 0009.
 */
public class FindAllRecruitmentInfoByCompanyIdPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Long companyId = (Long) params[0];
        Integer[] status = (Integer[]) params[1];
        Integer firstResult = (Integer) params[2];
        Integer sizeNo = (Integer) params[3];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from rec_info where company_id = ? and info_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by info_create_time desc");
        int count = 0;
        if (status != null) {
            count = status.length;
        }
        Object[] objects = new Object[1 + count];
        objects[0] = companyId;
        if (status != null) {

            for (int i = 0; i < status.length; i++) {
                objects[i + 1] = status[i];
            }
        }
        return findPager(executeSql.toString(), objects,firstResult,sizeNo, rowMapper);
    }
}
