package com.liuqi.nuna.livechat.other.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class SpringContextHandler implements ApplicationContextAware {


    private Logger logger = LoggerFactory.getLogger(SpringContextHandler.class);

    private static ApplicationContext applicationContext = null;

    /**
     * 用静态方法获取Spring Context
     * @return ApplicationContext
     */
    public static ApplicationContext getSpringContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 此方法由 spring 注入时调用
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("success setAppContext Of ApplicationContextLocal");
        SpringContextHandler.applicationContext = applicationContext;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2018/1/3 下午9:15
     * @param name
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {

        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 如果有多个Bean符合Class, 取出第一个.
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2018/1/3 下午9:15
     * @param clazz
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {

        checkApplicationContext();
        @SuppressWarnings("rawtypes")
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps!=null && !beanMaps.isEmpty()) {
            return (T) beanMaps.values().iterator().next();
        } else{
            return null;
        }
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext 未注入,请在applicationContext.xml中定义 SpringContextHandler");
        }
    }
}
