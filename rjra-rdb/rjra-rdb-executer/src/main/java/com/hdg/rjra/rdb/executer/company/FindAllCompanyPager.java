package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class FindAllCompanyPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Integer[] status = (Integer[]) params[0];
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_company where company_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by company_create_time desc");
        return findPager(executeSql.toString(), status,firstResult,sizeNo, rowMapper);
    }
}
