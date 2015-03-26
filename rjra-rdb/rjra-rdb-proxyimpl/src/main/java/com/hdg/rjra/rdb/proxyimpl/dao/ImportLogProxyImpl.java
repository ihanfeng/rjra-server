package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IImportLogProxy;
import com.hdg.rjra.rdb.proxy.domain.ImportLog;

import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class ImportLogProxyImpl extends BaseProxy implements IImportLogProxy {

    private static String moduleName = "rdb-import-log";

    @Override
    public Long saveImportLog(ImportLog importLog) {
        return daoClient.invoke(moduleName, "saveImportLog",
                new Object[]{importLog});
    }
}