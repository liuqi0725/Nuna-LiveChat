package com.liuqi.nuna.livechat.dao.mapper;

import com.liuqi.nuna.livechat.other.base.BaseDao;
import com.liuqi.nuna.livechat.pojo.TestUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TestUserMapper extends BaseDao {

    public List<TestUser> queryById(@Param("id") Integer uid){

        return this.getSqlSession().selectList("queryUserById",uid);
    }

}
