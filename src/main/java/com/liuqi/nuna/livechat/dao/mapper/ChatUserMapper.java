package com.liuqi.nuna.livechat.dao.mapper;

import com.liuqi.nuna.livechat.other.base.BaseDao;
import com.liuqi.nuna.livechat.pojo.ChatUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午6:00 2018/1/4
 */
@Repository
public class ChatUserMapper extends BaseDao{

    public ChatUser getUserByEmail(@Param("email")String email) {
        return this.getSqlSession().selectOne("queryByEmail",email);
    }

    public ChatUser getUserByPhone(@Param("phone")String phone) {
        return this.getSqlSession().selectOne("queryByPhone",phone);
    }

    public ChatUser getUserById(@Param("id")Integer id) {
        return this.getSqlSession().selectOne("queryById",id);
    }

    public Integer saveUser(ChatUser user){
        return this.getSqlSession().insert("save" , user);
    }

    public void updateChatUser(ChatUser user) {
        this.getSqlSession().update("updateChatUser" ,user);
    }
}
