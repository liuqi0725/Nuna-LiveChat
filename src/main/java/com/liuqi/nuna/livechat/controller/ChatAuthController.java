package com.liuqi.nuna.livechat.controller;

import com.liuqi.nuna.livechat.other.base.BaseController;
import com.liuqi.nuna.livechat.other.tool.CommonTools;
import com.liuqi.nuna.livechat.service.ChatAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "nunachat")
public class ChatAuthController extends BaseController{

    @Autowired
    ChatAuthService chatAuthService;

    @RequestMapping(value = "/auth/domain",method = RequestMethod.GET)
    @ResponseBody
    public Object getUserJson(@RequestParam String host, @RequestParam String siteKey,
                              HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getHeader("Host"));
        System.out.println(request.getHeader("Origin"));
        System.out.println(request.getHeader("Referer"));
        System.out.println(request.getHeader("User-Agent"));

        String msg = "";
        boolean status = false;

        String callback = request.getParameter("callback");

        String user_Agent = request.getHeader("User-Agent");
        if(CommonTools.validationUserAgent(user_Agent)){

            //开始验证
            status = chatAuthService.validationDomain(host,siteKey);

            status = true;

            if(!status)
                msg = "站点信息不正确或服务出现故障，没有查找到您的服务信息。";
        }
        else {
            msg = "对不起，不能对您开放服务，您可能是一个机器人。";
        }

        Map<String, Object> res = renderJSON(status,null,msg);

        //判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            msg = "无法识别的请求类型";
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(res);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

        return res;
    }
}
