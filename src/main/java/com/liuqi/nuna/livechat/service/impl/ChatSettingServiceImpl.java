package com.liuqi.nuna.livechat.service.impl;

import com.liuqi.nuna.livechat.dao.mapper.ChatSettingMapper;
import com.liuqi.nuna.livechat.pojo.ChatSetting;
import com.liuqi.nuna.livechat.service.ChatSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:35 2018/1/3
 */
@Service
public class ChatSettingServiceImpl implements ChatSettingService{

    @Autowired
    ChatSettingMapper mapper;

    public List<ChatSetting> getChatSettingAll() {
        return mapper.getChatSettingAll();
    }

    public ChatSetting getChatSettingById(Integer id) {
        return mapper.getChatSettingById(id);
    }

    public ChatSetting getChatSettingByKey(String key) {
        return mapper.getChatSettingByKey(key);
    }

    public List<ChatSetting> getChatSettingWithNormal() {
        return mapper.getChatSettingWithNormal();
    }

    public List<ChatSetting> getChatSettingWithStop() {
        return mapper.getChatSettingWithStop();
    }

    public List<ChatSetting> getChatSettingWithDelete() {
        return mapper.getChatSettingWithDelete();
    }
}
