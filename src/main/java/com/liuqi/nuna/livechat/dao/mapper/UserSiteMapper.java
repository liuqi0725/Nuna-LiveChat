package com.liuqi.nuna.livechat.dao.mapper;

import com.liuqi.nuna.livechat.other.base.BaseDao;
import com.liuqi.nuna.livechat.pojo.UserSite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午4:49 2017/12/29
 */
@Repository
public class UserSiteMapper extends BaseDao{

    /**
     * 查询全部站点
     * @param params
     * @return site 集合
     */
    public List<UserSite> queryUserSiteAll(Map<String,Object> params){
        return null;
    }

    /**
     * 通过用户 ID 查询 userSite
     * @param uid
     * @return site 集合
     */
    public List<UserSite> queryUserSite(@Param("uid")Integer uid){
        return this.getSqlSession().selectOne("queryUserSiteWithUid",uid);
    }

    /**
     * 通用 id、用户 id，查找唯一的 site
     * @param id
     * @param uid
     * @return
     */
    public UserSite queryUserSite(Integer id , Integer uid){
        UserSite site = new UserSite();
        site.setId(id);
        site.setUid(uid);
        return this.getSqlSession().selectOne("queryUserSiteWithOne",site);
    }
}
