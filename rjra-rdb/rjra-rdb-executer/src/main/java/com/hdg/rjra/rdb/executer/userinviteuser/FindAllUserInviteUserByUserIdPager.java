package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.base.enumerate.UserInviteUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindAllUserInviteUserByUserIdPager extends AbstractExecuter {
    static String sql = "select * from user_invite_user where user_id = ? and user_invite_user_status=" + UserInviteUserStatus.Active.getCode();

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