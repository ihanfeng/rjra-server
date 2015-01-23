package com.hdg.rjra.rdb.server.rpcserver;

import com.alibaba.fastjson.JSON;
import com.hdg.rjra.base.serializer.ByteSerializer;
import com.hdg.rjra.rdb.handler.IRequestHandler;
import com.hdg.rjra.rdb.model.thrift.ResponseModel;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.thrift.DaoProxyRpc;
import com.hdg.rjra.rdb.thrift.Request;
import com.hdg.rjra.rdb.thrift.Response;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2014/10/16.
 */
public class DaoProxyRpcImpl implements DaoProxyRpc.Iface {
    private static Logger logger = LoggerFactory.getLogger(DaoProxyRpcImpl.class);
    private ByteSerializer byteSerializer = new ByteSerializer();

    private IRequestHandler requestHandler;

    public IRequestHandler getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(IRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Response execRequest(Request request) throws TException {
        String requestId = request.getRequestId();
        Response response = new Response();
        response.setRequestId(requestId);
        String className = request.getClassName();
        String methodName = request.getMethodName();
        logger.debug("requestId >>> " + requestId + "----------------- 接受到请求 ---------------------------");
        logger.debug("requestId >>> " + requestId + "; module >>>>>>>>>>>> " + className);
        logger.debug("requestId >>> " + requestId + "; execute >>>>>>>>>>>> " + methodName);
        ResponseModel responseModel = null;
        try {
            List<ByteBuffer> bytes = request.getParams();
            List params = new ArrayList(bytes.size());
            logger.debug("requestId >>> " + requestId + "; param size >>> " + bytes.size());
            for (int i = 0; i < bytes.size(); i++) {
                logger.debug("xxxxxxxxxxxx param start  xxxxxxxxxxxxxxx");
                Object paramObject = byteSerializer.deserialize(bytes.get(i).array());
                params.add(paramObject);

                if (paramObject == null) {
                    logger.error("requestId >>> " + requestId + " ; 参数为null ");
                } else {
                    logger.debug("requestId >>> " + requestId + " ; value >> " + String.valueOf(paramObject));
                    logger.debug("requestId >>> " + requestId + " ; param class >>>> " + paramObject.getClass());
                    logger.debug("requestId >>> " + requestId + " ; param value >>>> " + JSON.toJSONString(paramObject));
                }
                logger.debug("xxxxxxxxxxx  param end  xxxxxxxxxxxxxxxx");
            }

            //默认走spring反射
            responseModel = requestHandler.handler(className, methodName, params);
            response.setValue(byteSerializer.serialize(responseModel.getResult()));
            response.setResultType(responseModel.getResultType().getCode());
            response.setMessage(responseModel.getMessage());
        } catch (Exception e) {
            responseModel = new ResponseModel();
            logger.error("业务调用调用失败 >>>> ", e);
            responseModel.setResultType(ResultType.BUSINESS_ERROR);
            responseModel.setMessage(e.getMessage());
        }
        logger.debug("responseModel >>> " + JSON.toJSONString(responseModel));
        logger.debug("----------------- 组装返回值完成 ---------------------------");
        return response;
    }
}
