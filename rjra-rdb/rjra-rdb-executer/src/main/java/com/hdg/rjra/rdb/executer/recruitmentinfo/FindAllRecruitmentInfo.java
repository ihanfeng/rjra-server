package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAllRecruitmentInfo extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Integer[] status = (Integer[]) params[0];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from rec_info where info_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by info_create_time desc");
        return getJdbcTemplate().query(executeSql.toString(), status, rowMapper);
    }
}
