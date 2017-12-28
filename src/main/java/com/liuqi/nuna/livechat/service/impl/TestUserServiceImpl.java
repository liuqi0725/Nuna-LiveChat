package com.liuqi.nuna.livechat.service.impl;


import com.liuqi.nuna.livechat.dao.mapper.TestUserMapper;
import com.liuqi.nuna.livechat.pojo.TestUser;
import com.liuqi.nuna.livechat.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper userMapper;

    public List<TestUser> getUsers(Integer userId) {

        return userMapper.queryById(userId);
    }
}
