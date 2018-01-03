package com.liuqi.nuna.livechat.controller;

import com.liuqi.nuna.livechat.pojo.TestUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 上午10:47 2018/1/3
 */
@Controller
@RequestMapping(value = "agent")
public class AgentController {

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ModelAndView agent(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("management/agent");
        return modelAndView;
    }
}
