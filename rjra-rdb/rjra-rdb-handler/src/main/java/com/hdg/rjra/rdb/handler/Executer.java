package com.hdg.rjra.rdb.handler;

import com.hdg.rjra.rdb.model.thrift.ResponseModel;

/**
 * 不是线程安全，不要将变量保存在属性中
 * Created by Rock on 2014/10/20.
 */
public interface Executer {
    public ResponseModel executeMethod(Object[] params);
}
