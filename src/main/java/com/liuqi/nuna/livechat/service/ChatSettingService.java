package com.liuqi.nuna.livechat.service;

import com.liuqi.nuna.livechat.pojo.ChatSetting;

import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:26 2018/1/3
 */
public interface ChatSettingService {

    /**
     * 查询所有的配置
     * @author liu.qi
     * @return
     */
    public List<ChatSetting> getChatSettingAll();

    /**
     * 根据 ID 查询配置值
     * @param id
     * @return
     */
    public ChatSetting getChatSettingById(Integer id);

    /**
     * 根据 KEY 查询配置值
     * @param key
     * @return
     */
    public ChatSetting getChatSettingByKey(String key);

    /**
     * 查询所有状态为 0（正常）的配置
     * @return
     */
    public List<ChatSetting> getChatSettingWithNormal();

    /**
     * 查询所有状态为 1（停用）的配置
     * @return
     */
    public List<ChatSetting> getChatSettingWithStop();

    /**
     * 查询所有状态为 2（删除）的配置
     * @return
     */
    public List<ChatSetting> getChatSettingWithDelete();

}
