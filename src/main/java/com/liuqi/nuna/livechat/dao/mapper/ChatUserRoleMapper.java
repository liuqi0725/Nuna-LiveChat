package com.liuqi.nuna.livechat.dao.mapper;

import com.liuqi.nuna.livechat.other.base.BaseDao;
import com.liuqi.nuna.livechat.pojo.ChatSetting;
import com.liuqi.nuna.livechat.pojo.ChatUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:35 2018/1/3
 */
@Repository
public class ChatUserRoleMapper extends BaseDao {


    public ChatUserRole getRoleById(@Param("id")Integer id) {
        return this.getSqlSession().selectOne("queryRoleById",id);
    }

    public ChatUserRole getRoleByName(@Param("name")String name) {
        return this.getSqlSession().selectOne("queryRoleByName",name);
    }

    public ChatUserRole getDefault(){
        return this.getSqlSession().selectOne("queryRoleDefault");
    }

}
