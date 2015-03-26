package com.hdg.rjra.rdb.executer.work;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.Work;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Administrator on 2015/3/26.
 */
public class FindAllWorkByConditionPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Work work = (Work) params[0];
        SqlParam sqlParam = SqlUtils.buildSelectSqlByDomain(work);
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];
        return findPager(sqlParam.getSql(), sqlParam.getObjects().toArray() , firstResult, sizeNo, rowMapper);
    }
}
