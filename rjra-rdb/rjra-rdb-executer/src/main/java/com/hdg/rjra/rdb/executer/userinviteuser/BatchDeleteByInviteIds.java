package com.hdg.rjra.rdb.executer.userinviteuser;

import com.hdg.rjra.base.enumerate.UserInviteUserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class BatchDeleteByInviteIds extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            List<Long> inviteIds = (List<Long>) params[0];
            StringBuffer executeSql = new StringBuffer();
            executeSql.append("UPDATE user_invite_user SET user_invite_user_status=");
            executeSql.append(UserInviteUserStatus.Delete.getCode());
            executeSql.append(" where invite_id in (-1");
            for (int i = 0; i < inviteIds.size(); i++) {
                executeSql.append(",");
                executeSql.append(inviteIds.get(i));
            }
            executeSql.append(")");
            getJdbcTemplate().update(executeSql.toString());
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
