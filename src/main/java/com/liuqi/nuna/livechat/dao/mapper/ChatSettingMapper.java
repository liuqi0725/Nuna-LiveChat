package com.liuqi.nuna.livechat.dao.mapper;

import com.liuqi.nuna.livechat.other.base.BaseDao;
import com.liuqi.nuna.livechat.pojo.ChatSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:35 2018/1/3
 */
@Repository
public class ChatSettingMapper extends BaseDao {


    public List<ChatSetting> getChatSettingAll() {
        return this.getSqlSession().selectList("queryAll");
    }

    public ChatSetting getChatSettingById(@Param("id") Integer id) {
        return this.getSqlSession().selectOne("queryById",id);
    }

    public ChatSetting getChatSettingByKey(@Param("key_name") String key_name) {
        return this.getSqlSession().selectOne("queryByKey",key_name);
    }

    public List<ChatSetting> getChatSettingWithNormal() {
        return this.getSqlSession().selectList("queryByNomarlStatus");
    }

    public List<ChatSetting> getChatSettingWithStop() {
        return this.getSqlSession().selectList("queryByStopStatus");
    }

    public List<ChatSetting> getChatSettingWithDelete() {
        return this.getSqlSession().selectList("queryByDeleteStatus");
    }

}
