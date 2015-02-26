package com.hdg.rjra.rdb.client;

import com.alibaba.fastjson.JSON;
import com.hdg.common.serializer.ByteSerializer;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.thrift.DaoProxyRpc;
import com.hdg.rjra.rdb.thrift.Request;
import com.hdg.rjra.rdb.thrift.Response;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Rock on 2014/10/16.
 */
public class RdbDefaultClient implements Client {
    private static Logger logger = LoggerFactory.getLogger(RdbDefaultClient.class);
    protected ByteSerializer byteSerializer = new ByteSerializer();

    private String host;
    private int port;
    private int timeOut;

    public RdbDefaultClient(String host, Integer port) throws Exception {
        this.host = host;
        this.port = port;
        this.timeOut = 180000; //3 minutes
    }

    public RdbDefaultClient(String host, int port, int timeOut) {
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
    }

    @Override
    public <T> T invoke(String className, String methodName, Object[] params) {
        Request request = new Request();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(className);
        request.setMethodName(methodName);
        List<ByteBuffer> byteBufferList = new ArrayList<ByteBuffer>(params.length);
        logger.debug(" methodName >>>> " + methodName);
        for (Object param : params) {
            logger.debug(" param >>>> " + JSON.toJSONString(param));
            byte[] bytes = byteSerializer.serialize(param);
            byteBufferList.add(ByteBuffer.wrap(bytes));
        }
        request.setParams(byteBufferList);
        TSocket streamTransport = null;
        try {
            streamTransport = new TSocket(host, port, timeOut);
            DaoProxyRpc.Client client = getClient(streamTransport);
            Response response = client.execRequest(request);
            if (response.getResultType().equals(ResultType.SUCCESS.getCode())) {
                byte[] bytes = response.getValue();
                T t = (T) byteSerializer.deserialize(bytes);
                logger.debug("返回结果 >>>> " + JSON.toJSONString(t));
                return t;
            } else {
                logger.error(" 业务异常 >>>>> " + response.getMessage());
                logger.error("业务异常 host >>>> {} , port >>>> {}",host,port);
                throw new RuntimeException(response.getMessage());
            }
        } catch (Exception e) {
            logger.error("链接异常 host >>>> {} , port >>>> {}",host,port);
            logger.error(" thrift 异常 >>>>> ", e);
            throw new RuntimeException(e);
        } finally {
            if (streamTransport != null) {
                streamTransport.close();
            }
        }
    }

    private DaoProxyRpc.Client getClient(TSocket streamTransport) throws TException {
        try {
            DaoProxyRpc.Client client = new DaoProxyRpc.Client(new TBinaryProtocol(streamTransport));
            if (!streamTransport.isOpen()) {
                streamTransport.open();
            }
            return client;
        } catch (TException e){
            logger.error("链接异常 host >>>> {} , port >>>> {}",host,port);
            throw e;
        }
    }

    @Override
    public void close() throws IOException {
    }
}
