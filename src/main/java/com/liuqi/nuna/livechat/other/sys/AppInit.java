package com.liuqi.nuna.livechat.other.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class AppInit extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(AppInit.class);


    @Override
    public void init() throws ServletException {
        logger.info("初始化系统.");

        ServletContext context = getServletContext();

        String webPath = context.getRealPath("/");

        if (!webPath.endsWith("\\") && !webPath.endsWith("/")) {
            if (webPath.lastIndexOf("\\") >= 0) {
                webPath = webPath + "\\";
            } else {
                webPath = webPath + "/";
            }
        }

        SystemParams.APP_REAL_PATH = webPath;
        logger.info("系统配置 ：APP_REAL_PATH = {}",SystemParams.APP_REAL_PATH);

        SystemParams.APP_PROJECT_NAME = context.getContextPath();
        logger.info("系统配置 ：APP_PROJECT_NAME = {}",SystemParams.APP_PROJECT_NAME);

        SystemParams.APP_CONFIG_PATH = SystemParams.APP_REAL_PATH + "WEB-INF" + File.separator + "classes" + File.separator + "config" + File.separator;
        logger.info("系统配置 ：APP_CONFIG_PATH = {}", SystemParams.APP_CONFIG_PATH);

        /*
         * 可以读取 web.xml 配置
         *  <context-param>
         *      <param-name>environment_name</param-name> <!-- 自定义key 值 -->
         *      <param-value>environment_value</param-value> <!-- 自定义value 值 -->
         *  </context-param>
         *
         *  通过context.getInitParameter("environment_name") 获取 web.xml中配置的变量
         */

        //执行系统变量赋值、查询数据库、初始化业务逻辑等

        AppEnvInit.Init();


        logger.info("初始化系统.完毕.");

        logger.info("----------------------------------");    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
