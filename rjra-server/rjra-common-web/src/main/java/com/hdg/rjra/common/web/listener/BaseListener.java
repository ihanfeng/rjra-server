package com.hdg.rjra.common.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;


/**
 * @author Administrator
 */
public class BaseListener extends ContextLoaderListener {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(BaseListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        LOG.info("~~~~~~~~~~~~~~~~~~~~初始化配置~~~~~~~~~~~~~~~~~~~~~");

        // 加载配置
        loadPlatformConfig(event);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
        LOG.info("~~~~~~~~~~~~~~~~~~~~程序结束~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     *
     */
    private void loadPlatformConfig(ServletContextEvent event) {
        WebApplicationContext webcontext = ContextLoader.getCurrentWebApplicationContext();
//        AreaTreeService areaTreeService = webcontext.getBean(AreaTreeService.class);
    }
}
