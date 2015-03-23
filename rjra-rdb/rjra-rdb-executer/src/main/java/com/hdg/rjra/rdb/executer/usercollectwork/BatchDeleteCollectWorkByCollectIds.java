package com.hdg.rjra.rdb.executer.usercollectwork;

import com.hdg.rjra.base.enumerate.UserCollectWorkStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class BatchDeleteCollectWorkByCollectIds extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            List<Long> collectIds = (List<Long>) params[0];
            StringBuffer executeSql = new StringBuffer();
            executeSql.append("UPDATE user_collect_work SET user_collect_work_status=");
            executeSql.append(UserCollectWorkStatus.Delete.getCode());
            executeSql.append(" where collect_id in (-1");
            for (int i = 0; i < collectIds.size(); i++) {
                executeSql.append(",");
                executeSql.append(collectIds.get(i));
            }
            executeSql.append(")");
            getJdbcTemplate().update(executeSql.toString());
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
