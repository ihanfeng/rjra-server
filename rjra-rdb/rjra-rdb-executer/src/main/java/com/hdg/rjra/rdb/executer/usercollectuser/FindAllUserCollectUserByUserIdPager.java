package com.hdg.rjra.rdb.executer.usercollectuser;

import com.hdg.rjra.base.enumerate.UserCollectUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserCollectUserByUserIdPager extends AbstractExecuter {
    static String sql = "select * from user_collect_user where user_id = ? and user_collect_user_status=" + UserCollectUserStatus.Active.getCode();
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