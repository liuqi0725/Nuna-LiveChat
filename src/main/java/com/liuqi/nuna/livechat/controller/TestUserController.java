package com.liuqi.nuna.livechat.controller;


import com.liuqi.nuna.livechat.pojo.TestUser;
import com.liuqi.nuna.livechat.service.TestUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/test")
public class TestUserController {

    private static final Logger logger = LoggerFactory.getLogger(TestUserController.class);

    @Autowired
    private TestUserService userService;

    //返回jsp视图展示 springmvc 返回 ModelAndView对象
    //freemark 返回 字符串 String  http://localhost:8080/test/getUserView?userId=1
    @RequestMapping(value = "/getUserView",method = RequestMethod.GET)
    public ModelAndView getUserView(@RequestParam Integer userId,ModelMap map) {
        ModelAndView modelAndView = new ModelAndView();
        //调用service方法得到用户列表
        List<TestUser> users = userService.getUsers(userId);
        //将得到的用户列表内容添加到ModelAndView中

        //设置响应的jsp视图
        //modelAndView.addObject("users",users);
        //modelAndView.setViewName("getUsers");
        logger.info("===============================成功查询用户列表！");

        //设置响应的jsp视图 时，返回 ModelAndView 对象
        //return modelAndView;

        //设置为 freemaker 时，返回视图名称
        modelAndView.setViewName("front/testUser");
        modelAndView.addObject("users",users);
        return modelAndView;
        //return "users";
    }
    //返回json格式数据，形式1  http://localhost:8080/test/getUserJson/1
    @RequestMapping(value = "/getUserJson/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public List getUserJson(@PathVariable Integer userId, HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        List<TestUser> users = userService.getUsers(userId);
        logger.info("===============================成功查询用户列表！");
        return users;
    }
    //返回json格式数据，形式2（自定义了返回的格式）
    @RequestMapping(value = "/getUserJson2",method = RequestMethod.GET)
    public void getUserJson2(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        List<TestUser> users = userService.getUsers(userId);
        logger.info("===============================成功查询用户列表！");
        renderSuccessString(response, users);
    }

    private void renderSuccessString(HttpServletResponse response, List<TestUser> users) {

    }
}
