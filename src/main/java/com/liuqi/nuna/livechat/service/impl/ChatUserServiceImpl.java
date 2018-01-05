package com.liuqi.nuna.livechat.service.impl;

import com.liuqi.nuna.livechat.dao.mapper.ChatUserMapper;
import com.liuqi.nuna.livechat.dao.mapper.ChatUserRoleMapper;
import com.liuqi.nuna.livechat.other.sys.AppConnstants;
import com.liuqi.nuna.livechat.other.tool.CommonTools;
import com.liuqi.nuna.livechat.pojo.ChatUser;
import com.liuqi.nuna.livechat.pojo.ChatUserRole;
import com.liuqi.nuna.livechat.service.ChatMailService;
import com.liuqi.nuna.livechat.service.ChatUserService;
import com.liuqi.nuna.module.role.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午5:56 2018/1/4
 */

@Service
public class ChatUserServiceImpl implements ChatUserService{

    @Autowired
    ChatUserMapper chatUserMapper;

    @Autowired
    ChatUserRoleMapper chatUserRoleMapper;

    @Autowired
    ChatMailService mailService;

    public ChatUser login(String login_col , Object login_val , String password){
        ChatUser user = null;

        if(login_col.equals(AppConnstants.LOGIN_FIELD_EMAIL)){
            user = chatUserMapper.getUserByEmail(String.valueOf(login_val));
        }else if(login_col.equals(AppConnstants.LOGIN_FIELD_PHONE)){
            user = chatUserMapper.getUserByPhone(String.valueOf(login_val));
        }else if(login_col.equals(AppConnstants.LOGIN_FIELD_ID)){
            user = chatUserMapper.getUserById((Integer)login_val);
        }

        //验证登陆
        if(user != null && user.validatePassword(password)){
            //获取权限
            ChatUserRole role = chatUserRoleMapper.getRoleById(user.getRole_id());
            user.setPermissions(role.getPermissions());
        }

        return user;
    }

    public ChatUser register(String name, String email, String phone, String password) {

        //注册

        //获取默认权限
        ChatUserRole role = chatUserRoleMapper.getDefault();

        ChatUser user = new ChatUser();
        user.setRole_id(role.getId());
        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(CommonTools.Str2MD5(password,true));

        int flag = chatUserMapper.saveUser(user);

        if(flag > 0){
            user = chatUserMapper.getUserByEmail(email);
            //发送验证邮件
            mailService.sendConfirmMail(user);

            return user;
        }

        return null;
    }

    public boolean registerConfirmed(String token , ChatUser user) {

        if(CommonTools.check_user_token(token,"id",String.valueOf(user.getId()))){
            //修改用户 confirmd
            user.setConfirmed(0);
            //注册确认，修改用户为管理员
            ChatUserRole role = chatUserRoleMapper.getRoleByName(String.valueOf(Roles.CHAT_ADMIN_USER[0]));
            user.setRole_id(role.getId());
            chatUserMapper.updateChatUser(user);
            return true;
        }

        return false;

    }
}
