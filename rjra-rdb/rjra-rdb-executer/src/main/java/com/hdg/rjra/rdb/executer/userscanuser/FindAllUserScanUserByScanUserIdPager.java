package com.hdg.rjra.rdb.executer.userscanuser;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserScanUserByScanUserIdPager extends AbstractExecuter {

    static String sql = "select * from user_scan_user where scan_user_id=?";

    @Override
    public Object execute(Object[] params) {
        Long scanUserId = (Long) params[0];
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];;
        List<Object> objects = new ArrayList<Object>();
        objects.add(scanUserId);
        return findPager(sql, objects.toArray(), firstResult,sizeNo, rowMapper);
    }
}
