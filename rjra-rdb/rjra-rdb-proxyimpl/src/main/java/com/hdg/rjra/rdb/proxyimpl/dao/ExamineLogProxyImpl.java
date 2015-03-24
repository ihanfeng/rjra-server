package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IExamineLogProxy;
import com.hdg.rjra.rdb.proxy.domain.ExamineLog;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class ExamineLogProxyImpl extends BaseProxy implements IExamineLogProxy {

    private static String moduleName = "rdb-examine-log";

    @Override
    public Long saveExamineLog(ExamineLog examineLog) {
        return daoClient.invoke(moduleName, "saveExamineLog",
                new Object[]{examineLog});
    }

    @Override
    public List<ExamineLog> findExamineLogByResourceId(Long resourceId) {
        return daoClient.invoke(moduleName, "findExamineLogByResourceId",
                new Object[]{resourceId});
    }
}
