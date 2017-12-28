package com.liuqi.nuna.livechat.service;

import com.liuqi.nuna.livechat.pojo.TestUser;

import java.util.List;

public interface TestUserService {

    public List<TestUser> getUsers(Integer userId);
}
