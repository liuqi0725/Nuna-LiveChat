package com.liuqi.nuna.livechat.other.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHandler implements ApplicationContextAware {


    private Logger logger = LoggerFactory.getLogger(SpringContextHandler.class);

    private static ApplicationContext appContext = null;

    /**
     * 用静态方法获取Spring Context
     * @return ApplicationContext
     */
    public static ApplicationContext getSpringContext() {
        return appContext;
    }

    /**
     * 此方法由 spring 注入时调用
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("success setAppContext Of ApplicationContextLocal");
        SpringContextHandler.appContext = appContext;
    }
}
