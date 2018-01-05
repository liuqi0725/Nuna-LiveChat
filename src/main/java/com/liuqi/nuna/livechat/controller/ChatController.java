package com.liuqi.nuna.livechat.controller;

import com.liuqi.nuna.livechat.other.sys.AppConnstants;
import com.liuqi.nuna.livechat.pojo.ChatUser;
import com.liuqi.nuna.livechat.service.ChatUserService;
import com.liuqi.nuna.module.annotation.RequireLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @Author : alexliu
 * @Description : chat 的入口 注册等
 * @Date : Create at 下午3:10 2018/1/4
 */

@Controller
@RequestMapping
public class ChatController {

    private static Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    ChatUserService chatUserService;

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();

        if(request.getMethod().equals("GET")){
            modelAndView.setViewName("login");
        }

        if(request.getMethod().equals("POST")){
            ChatUser user = chatUserService.login(AppConnstants.LOGIN_FIELD_EMAIL, request.getParameter("email"),request.getParameter("password"));//

            if(user == null){
                modelAndView.setViewName("login");
                modelAndView.addObject("message","登陆失败，用户不存在或用户名密码错误!");
            }else{
                //保存 session
                request.getSession().setAttribute("user",user);

                //已确认邮箱 跳转 index ,未确认跳转unconfirmed
                if(user.getConfirmed() == 0)
                    modelAndView.setViewName("redirect:/management/index");
                else
                    modelAndView.setViewName("redirect:/management/unconfirmed");
            }
        }

        return modelAndView;
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register",method=RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            ChatUser user = chatUserService.register(
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("phone"),
                    request.getParameter("password")
            );

            if (user != null){
                //保存 session
                request.getSession().setAttribute("user", user);
                //跳转 unconfirmed
                modelAndView.setViewName("redirect:/management/unconfirmed");
            }else{
                throw new Exception("[NOT ERROR]Register user maybe registerd !");
            }
        } catch (Exception e){
            logger.error("Register Function error ,e >> {}" , e.getMessage());
            e.printStackTrace();

            modelAndView.addObject("message","注册失败或该邮箱已被注册!");
            modelAndView.addObject("register",true);
            modelAndView.setViewName("/login");
        }

        return modelAndView;
    }

    /**
     * 注册邮箱确认
     * @param token
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/confirmed",method=RequestMethod.GET)
    @RequireLogin
    public ModelAndView confirmed(@RequestParam String token, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();

        try {

            ChatUser user = (ChatUser) request.getSession().getAttribute("user");

            if(chatUserService.registerConfirmed(token,user)){
                //更新 session 中的 user 值
                request.getSession().setAttribute("user", user);
                modelAndView.setViewName("redirect:/management/index");
            }else{
                throw new Exception("[NOT ERROR]Register user confirmed faild !");
            }

        } catch (Exception e){
            logger.error("Register user confirmed Function error ,e >> {}" , e.getMessage());
            e.printStackTrace();

            modelAndView.addObject("message","验证时间已过或信息不正确!");
            modelAndView.setViewName("/management/unconfirmed");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @RequireLogin
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
