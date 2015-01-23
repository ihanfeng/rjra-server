package com.hdg.rjra.rdb.server.rpcserver;

import com.hdg.rjra.rdb.thrift.DaoProxyRpc;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * Created by Rock on 2014/10/16.
 */
public class ThriftServer {
    private int port;
    private DaoProxyRpc.Iface daoProxy;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DaoProxyRpc.Iface getDaoProxy() {
        return daoProxy;
    }

    public void setDaoProxy(DaoProxyRpc.Iface daoProxy) {
        this.daoProxy = daoProxy;
    }

    public void start() throws Exception {
        TProcessor tprocessor = new DaoProxyRpc.Processor(daoProxy);
        TServerSocket serverTransport = new TServerSocket(port);
        TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
        ttpsArgs.processor(tprocessor);
        ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
        // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
        TServer server = new TThreadPoolServer(ttpsArgs);
        server.serve();
    }
}
