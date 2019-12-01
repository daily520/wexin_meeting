package com.qfjy.project.weixin.api.accesstoken;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/22 17:03
 */
@WebListener
public class AccessTokenListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务启动");
        new AccessTokenThread().start();
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
