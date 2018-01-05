package com.liuqi.nuna.livechat.service;

import com.liuqi.nuna.livechat.pojo.ChatUser;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午5:53 2018/1/4
 */
public interface ChatUserService {

    /**
     * 用户登录
     * @param login_col 用作 login 的字段，  支持 email，phone，id
     * @param login_val login 字段的值
     * @param password 密码(未加密)
     * @return login 后的对象
     */
    public ChatUser login(String login_col , Object login_val, String password);

    /**
     * 注册
     * @param name
     * @param email
     * @param phone
     * @param password
     * @return
     */
    public ChatUser register(String name, String email, String phone, String password);

    /**
     * 注册邮件确认
     * @param token
     * @param user
     * @return
     */
    public boolean registerConfirmed(String token,ChatUser user);

}
