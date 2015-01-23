package com.hdg.rjra.rdb.executer.employ;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class FindEmployByCompanyIdPager extends AbstractExecuter {

    String sql = "select * from rec_employ where company_id = ? and employ_company_status = ?";
    @Override
    public Object execute(Object[] params) {
        Long companyId = (Long) params[0];
        Integer status = (Integer) params[1];
        Integer firstResult = (Integer) params[2];
        Integer sizeNo = (Integer) params[3];
        Object[] param = new Object[]{companyId, status};
        return findPager(sql, param, firstResult,sizeNo, rowMapper);
    }
}
