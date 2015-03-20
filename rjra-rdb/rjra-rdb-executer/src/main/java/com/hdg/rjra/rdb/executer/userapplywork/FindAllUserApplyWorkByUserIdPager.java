package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserApplyWorkByUserIdPager extends AbstractExecuter {
    static String sql = "select * from user_apply_work where user_id = ? and user_apply_work_status = " + UserApplyWorkStatus.Active.getCode();
    @Override
    public Object execute(Object[] params) {
        Long userId = (Long) params[0];
        Integer firstResult = (Integer) params[1];
        Integer sizeNo = (Integer) params[2];;
        List<Object> objects = new ArrayList<Object>();
        objects.add(userId);
        return findPager(sql, objects.toArray(), firstResult, sizeNo, rowMapper);
    }
}
