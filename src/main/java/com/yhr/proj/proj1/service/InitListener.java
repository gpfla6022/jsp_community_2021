package com.yhr.proj.proj1.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yhr.proj.proj1.app.App;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	App.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
