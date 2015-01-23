package com.hdg.rjra.rdb.handler;

import com.hdg.rjra.rdb.model.thrift.ResponseModel;

import java.util.List;

/**
 * Created by Rock on 2014/10/16.
 */
public interface IRequestHandler {
    ResponseModel handler(String className, String methodName, List<Object> params);
}
