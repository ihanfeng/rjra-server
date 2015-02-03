package com.hdg.rjra.rdb.server.webserver;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 * Created by infi on 2015/1/26.
 */
public class RjraRdbWebServer {
    private static Logger logger = LoggerFactory.getLogger(RjraRdbWebServer.class);

    private int webport;

    public void start() {
        new Thread() {
            @Override
            public void run() {
                this.setName("Rdb --web");
                try {
                    Server server = new Server();
                    SelectChannelConnector ret = new SelectChannelConnector();
                    ret.setAcceptQueueSize(128);
                    ret.setResolveNames(false);
                    ret.setUseDirectBuffers(false);
                    ret.setHost("0.0.0.0");
                    ret.setPort(webport);
                    server.addConnector(ret);
                    WebAppContext context = new WebAppContext();
                    context.setContextPath("/");

                    String serverHome = System.getProperty("SERVER_HOME");
                    logger.info("serverHome >>>> " + serverHome);
                    File file = null;
                    if (StringUtils.isBlank(serverHome)) {
                        logger.error("is not found SERVER_HOME");
                        URL url = RjraRdbWebServer.class.getClassLoader().getResource("");
                        file = new File(new File(url.getPath()), "webapp");
                    } else {
                        file = new File(new File(serverHome), "webapp");
                    }
                    logger.info("webapp >>>> " + file.getAbsolutePath());
                    logger.info("webapp >>>> " + file.getPath());
                    context.setResourceBase(file.getPath());

                    server.setHandler(context);
                    server.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public int getWebport() {
        return webport;
    }

    public void setWebport(int webport) {
        this.webport = webport;
    }
}
