package com.liuqi.nuna.module.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuqi.nuna.livechat.other.sys.SystemParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GlobalInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

        if(StringUtils.isEmpty(SystemParams.APP_WEB_PATH)){
            String scheme =  request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String path = request.getContextPath();
            //设置 webpath
            SystemParams.APP_WEB_PATH = scheme + "://" + serverName + ":" + port + path;
        }

        request.setAttribute("basePath", SystemParams.APP_WEB_PATH);
		
	}
}
