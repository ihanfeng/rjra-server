package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.ExamineLog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface IExamineLogProxy extends Serializable {

    public Long saveExamineLog(ExamineLog examineLog);

    public List<ExamineLog> findExamineLogByResourceId(Long resourceId);
}
