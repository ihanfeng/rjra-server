package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.ImportLog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public interface IImportLogProxy extends Serializable {

    public Long saveImportLog(ImportLog examineLog);

}