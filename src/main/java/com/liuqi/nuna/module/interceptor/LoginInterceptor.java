package com.liuqi.nuna.module.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liuqi.nuna.livechat.pojo.ChatUser;
import com.liuqi.nuna.module.annotation.RequireLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();

		//      1、Class.isAssignableFrom()作用与instanceof相似，  
		//      前者是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口  
		//      后者是用来判断一个对象实例是否是一个类或接口的或其子类子接口的实例  
		//      2、HandlerMethod及子类主要用于封装方法调用相关信息  
		//      简单点说我们可以通过这个类对象获得被调用方法的各种信息，如方法名、参数类型等  
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {    	
            Object bean = ((HandlerMethod)handler).getBean();
            Method method= ((HandlerMethod)handler).getMethod();
            Annotation classAnnotation = bean.getClass().getAnnotation(RequireLogin.class);//类上有该标记
            Annotation methodAnnotation = method.getAnnotation(RequireLogin.class);//方法上有该标记

            if(classAnnotation != null || methodAnnotation != null){
            	//检查用户登录
                ChatUser user = (ChatUser) session.getAttribute("user");
            	if(user == null){
            		//跳转登录
            		String a = request.getRequestURI();
            		response.sendRedirect("/login?next="+a);
        			return false;
            	}
            }
        }
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		//如果用户登录了 赋予对应的权限
		if(session.getAttribute("user") != null){
			
		}else{
			//如果未登录 赋予普通用户权限
			
		}
	}

}
