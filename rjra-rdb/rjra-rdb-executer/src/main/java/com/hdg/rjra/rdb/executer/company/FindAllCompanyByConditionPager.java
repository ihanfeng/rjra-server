package com.hdg.rjra.rdb.executer.company;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Administrator on 2015/3/24.
 */
public class FindAllCompanyByConditionPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Company company = (Company) params[0];
        SqlParam sqlParam = SqlUtils.buildSelectSqlByDomain(company);
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];
        return findPager(sqlParam.getSql(), sqlParam.getObjects().toArray() , firstResult, sizeNo, rowMapper);
    }
}