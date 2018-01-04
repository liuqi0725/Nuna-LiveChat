package com.liuqi.nuna.module.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title: RequireAdmin.java
 * @Package com.shenshu.deepCapture.dam.core.sys.annotation
 * @Description: 需要 Admin 才可执行
 * @author alexliu | liuqi_0725@aliyun.com 
 * @date 2017年11月24日 下午2:45:08
 * @version V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited //继承关系
public @interface RequireAdmin {

}
