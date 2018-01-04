package com.liuqi.nuna.livechat.other.sys;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午4:35 2017/12/29
 */
public class SystemParams {

    public static final String DES_KEY = "nuna-live-chat";

    /**
     * 一个agent 同时允许服务的用户数
     */
    public static Integer AGENT_SAME_TIME_SERVICE_NUMBER = 0;

    /**
     * 最大排队人数
     */
    public static Integer CHAT_MAX_QUEUE_NUMBER = 0;

    /**
     * 会话中客服超时结束会话时间。0 不超时。单位-秒
     */
    public static Integer CHAT_SESSIONG_AGENT_TIME_OUT = 0;

    /**
     * 会话中客服超时结束会话时间,0 不超时。单位-秒
     */
    public static Integer CHAT_SESSIONG_CUSTOMER_TIME_OUT = 0;

    /**
     * 项目 web 路径
     */
    public static String APP_WEB_PATH = "";
}
