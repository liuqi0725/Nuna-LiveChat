package com.liuqi.nuna.module.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title: RequirePermission.java
 * @Package com.shenshu.deepCapture.dam.core.sys.annotation
 * @Description: 需要权限才可执行
 * @author alexliu | liuqi_0725@aliyun.com 
 * @date 2017年11月24日 下午2:46:00
 * @version V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited //继承关系
public @interface RequirePermission {

	String[] permission();
}
