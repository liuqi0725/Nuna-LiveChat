package com.liuqi.nuna.livechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liuqi.nuna.livechat.dao.mapper.UserSiteMapper;
import com.liuqi.nuna.livechat.other.sys.SystemParams;
import com.liuqi.nuna.livechat.other.tool.DESTools;
import com.liuqi.nuna.livechat.pojo.UserSite;
import com.liuqi.nuna.livechat.service.ChatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午4:02 2017/12/29
 */
@Service
public class ChatAuthServiceImpl implements ChatAuthService{

    private static Logger logger = LoggerFactory.getLogger(ChatAuthServiceImpl.class);

    @Autowired
    UserSiteMapper userSiteMapper;

    public boolean validationDomain(String host, String siteKey) {

        //解密 sitekey
        DESTools des = new DESTools(SystemParams.DES_KEY);
        String decodeStr = "";
        try {
            decodeStr = des.decrypt(siteKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解密 siteinfo 失败。 sikekey >>> {}",siteKey);

            return false;
        }

        //转 json
        JSONObject userPostSiteInfo = JSONObject.parseObject(decodeStr);

        Integer id = userPostSiteInfo.getInteger("id");
        Integer uid = userPostSiteInfo.getInteger("uid");

        //用 uid 查询 usersite 表 获取 domain ，sitekey
        UserSite dbSiteInfo = userSiteMapper.queryUserSite(id,uid);

        if(dbSiteInfo == null)
            return false;

        //如果 domain、sitekey 均匹配，正确
        if(dbSiteInfo.getSite_domain().equalsIgnoreCase(userPostSiteInfo.getString("site_domain"))
                && siteKey.equalsIgnoreCase(dbSiteInfo.getSite_key())){

            return true;
        }

        return false;
    }
}
