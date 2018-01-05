package com.liuqi.nuna.livechat.controller;

import com.liuqi.nuna.livechat.other.sys.AppConnstants;
import com.liuqi.nuna.livechat.pojo.ChatUser;
import com.liuqi.nuna.livechat.service.ChatUserService;
import com.liuqi.nuna.module.annotation.RequireLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : alexliu
 * @Description : chat 的管理入口
 * @Date : Create at 下午3:10 2018/1/4
 */

@Controller
@RequestMapping("management")
public class ChatManagementController {

    private static Logger logger = LoggerFactory.getLogger(ChatManagementController.class);

    @Autowired
    ChatUserService chatUserService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @RequireLogin
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("management/index");
        return modelAndView;
    }

    @RequestMapping(value = "/unconfirmed",method = RequestMethod.GET)
    @RequireLogin
    public ModelAndView unconfirmed(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("management/unconfirmed");
        return modelAndView;
    }

}
