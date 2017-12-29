package com.liuqi.nuna.livechat.other.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午4:50 2017/12/29
 */
public class BaseDao extends SqlSessionDaoSupport {


    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
