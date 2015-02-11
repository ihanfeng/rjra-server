package com.hdg.rjra.rdb.server;

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.rdb.server.rpcserver.ThriftServer;
import com.hdg.rjra.rdb.server.webserver.RjraRdbWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Rock on 2014/10/20.
 */
public class RjraRdb {
    private static Logger logger = LoggerFactory.getLogger(RjraRdb.class);
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = null;
        applicationContext = new ClassPathXmlApplicationContext("rjra-rdb-server-bean.xml");
        RjraRdbWebServer rdbWebServer = applicationContext.getBean(RjraRdbWebServer.class);
        logger.info("server sql-monitor-port >>>>>> "+ CustomizedPropertyConfigurer.getContextPropertyForInteger("sql-monitor-port"));
        rdbWebServer.start();
        ThriftServer thriftServer = applicationContext.getBean(ThriftServer.class);
        logger.info("server thrift-port >>>>>> "+ CustomizedPropertyConfigurer.getContextPropertyForInteger("thrift-port"));
        thriftServer.start();
    }
}
