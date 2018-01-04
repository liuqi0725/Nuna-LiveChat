package com.liuqi.nuna.module.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liuqi.nuna.module.annotation.RequirePermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class PermissionInterceptor extends HandlerInterceptorAdapter{
	
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
            Annotation methodAnnotation = method.getAnnotation(RequirePermission.class);//方法上有该标记
            
            if(methodAnnotation != null){
            	//检查用户权限
//            	UserBean user = (UserBean) session.getAttribute("user");
//            	if(user == null){
//            		//跳转登录
//            		String a = request.getRequestURI();
//            		response.sendRedirect("/dam/auth/logintest?next="+a);	
//        			return false;
//            	}
            	
//                boolean isLogin = LoginUtil.checkIsLogin(request,response);
//                if(isLogin)
//                    return true;
//                else{//未登录
//                    if(isAjax(request)){
//                        //Ajax请求返回JSON
//                        HttpResultModel<Object> rep=new HttpResultModel<Object>();
//                        rep.setCode(-1);
//                        rep.setMsg("请登录后操作!");
//                        String data = JsonUtil.obj2string(rep);
//                        response.setHeader("content-type", "text/html;charset=UTF-8");
//                        OutputStream out = response.getOutputStream();
//                        out.write(data.getBytes("UTF-8"));
//                        return false;
//                    }
//                    response.sendRedirect(basePath);
//                }
            }
        }
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

}
