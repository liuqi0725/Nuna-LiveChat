package com.liuqi.nuna.livechat.service;

import com.liuqi.nuna.livechat.pojo.ChatUser;
import org.springframework.mail.SimpleMailMessage;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:50 2018/1/5
 */
public interface ChatMailService {


    /**
     * 发送注册确认邮件
     * @param user 注册用户
     */
    public void sendConfirmMail(ChatUser user);

}
