package com.liuqi.nuna.livechat.service;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午4:00 2017/12/29
 */
public interface ChatAuthService {

    /**
     * 验证站点信息
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2017/12/29 下午4:03
     * @param host, siteKey
     * @return boolean
     */
    public boolean validationDomain(String host,String siteKey);

}
