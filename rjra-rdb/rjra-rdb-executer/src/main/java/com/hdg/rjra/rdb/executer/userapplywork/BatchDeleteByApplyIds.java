package com.hdg.rjra.rdb.executer.userapplywork;

import com.hdg.rjra.base.enumerate.UserApplyWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class BatchDeleteByApplyIds extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            List<Long> applyIds = (List<Long>) params[0];
            StringBuffer executeSql = new StringBuffer();
            executeSql.append("UPDATE user_apply_work SET user_apply_work_status=");
            executeSql.append(UserApplyWorkStatus.Delete.getCode());
            executeSql.append(" where apply_id in (-1");
            for (int i = 0; i < applyIds.size(); i++) {
                executeSql.append(",");
                executeSql.append(applyIds.get(i));
            }
            executeSql.append(")");
            getJdbcTemplate().update(executeSql.toString());
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}