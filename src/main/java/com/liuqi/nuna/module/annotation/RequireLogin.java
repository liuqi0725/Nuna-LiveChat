package com.liuqi.nuna.module.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title: RequireLogin.java
 * @Package com.shenshu.deepCapture.dam.core.sys.annotation
 * @Description: 
 * 登录验证注解
 * 该注解可以标记Controller 或 Controller 中的方法.
 * 如果Controller 有该标记,那么这个Controller下面所有的方法都会被过滤器
 * 进行验证
 * 如果Controller 没有有该标记,但Controller中的某个方法拥有该标记
 * 那么这个方法将被过滤器验证(其他没有被标记的不会被验证)
 * @author alexliu | liuqi_0725@aliyun.com 
 * @date 2017年11月24日 上午9:33:55
 * @version V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited //继承关系
public @interface RequireLogin {
}