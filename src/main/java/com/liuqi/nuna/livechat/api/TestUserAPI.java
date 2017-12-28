package com.liuqi.nuna.livechat.api;


import com.liuqi.nuna.livechat.pojo.TestUser;
import com.liuqi.nuna.livechat.service.TestUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestUserAPI {

    private static final Logger logger = LoggerFactory.getLogger(TestUserAPI.class);

    @Autowired
    TestUserService userService;

    @RequestMapping(value="/queryUserById/{uid}")
    @ResponseBody
    public Object getUserById(@PathVariable Integer uid){
        List<TestUser> users = userService.getUsers(uid);
        logger.info("===============================成功查询用户列表！");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("status",true);
        map.put("data",users);
        map.put("msg","");

        return map;
    }

}
