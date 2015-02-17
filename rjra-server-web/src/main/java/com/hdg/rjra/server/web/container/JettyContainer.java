/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hdg.rjra.server.web.container;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.page.ResourceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.net.URL;

/**
 * JettyContainer. (SPI, Singleton, ThreadSafe)
 * 
 * @author ROCK
 */
public class JettyContainer implements Container {

    private static final Logger logger = LoggerFactory.getLogger(com.alibaba.dubbo.container.jetty.JettyContainer.class);

    public static final String JETTY_PORT = "dubbo.jetty.port";

    public static final int DEFAULT_JETTY_PORT = 8088;

    public static final String CONTEXT_PATH = "/rjra-server";

    SelectChannelConnector connector;

    public void start() {
        String serverPort = ConfigUtils.getProperty(JETTY_PORT);
        int port;
        if (serverPort == null || serverPort.length() == 0) {
            port = DEFAULT_JETTY_PORT;
        } else {
            port = Integer.parseInt(serverPort);
        }
        connector = new SelectChannelConnector();
        connector.setPort(port);
        WebAppContext context = new WebAppContext();
        context.setContextPath(CONTEXT_PATH);
        URL url = JettyContainer.class.getClassLoader().getResource("");
        File file = new File(new File(url.getPath()), "webapp");
        context.setResourceBase(file.getPath());
        Server server = new Server();
        server.addConnector(connector);
        server.setHandler(context);
        try {
            server.start();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to start jetty server on " + NetUtils.getLocalHost() + ":" + port + ", cause: " + e.getMessage(), e);
        }
    }

    public void stop() {
        try {
            if (connector != null) {
                connector.close();
                connector = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

}