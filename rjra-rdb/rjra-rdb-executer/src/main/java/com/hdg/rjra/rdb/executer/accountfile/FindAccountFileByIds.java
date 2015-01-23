package com.hdg.rjra.rdb.executer.accountfile;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class FindAccountFileByIds extends AbstractExecuter {
    @Override
    public Object execute(Object[] params) {
        Long[] fileIds = (Long[]) params[0];
        Integer[] status = (Integer[]) params[1];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from account_file where file_id in(");
        executeSql.append(SqlUtils.appendPlaceholder(fileIds.length));
        executeSql.append(") and file_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by file_upload_time desc");
        int fileIdsCount = fileIds.length;
        int count = status.length;
        Object[] objects = new Object[count + fileIdsCount];
        for (int i = 0; i < fileIdsCount; i++) {
            objects[i] = fileIds[i];
        }
        for (int i = fileIdsCount; i < count+fileIdsCount ; i++) {
            objects[i] = status[i-fileIdsCount];
        }
        return getJdbcTemplate().query(executeSql.toString(), objects, rowMapper);
    }
}
