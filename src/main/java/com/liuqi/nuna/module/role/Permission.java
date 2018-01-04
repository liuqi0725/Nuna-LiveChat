package com.liuqi.nuna.module.role;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:40 2018/1/4
 */
public class Permission {

    /**
     * 用户信息操作
     * 修改密码，修改邮箱，修改头像，修改昵称
     */
    public static final Integer CHANGE_USER_INFO = 0x01;

    /**
     * DashBoard 查看
     */
    public static final Integer DASHBOARD = 0x02;

    /**
     * 站点管理
     */
    public static final Integer SITE_MANAGE = 0x04;

    /**
     * 客服管理
     */
    public static final Integer USER_MANAGE = 0x08;

    /**
     * 实时对话
     */
    public static final Integer LIVE_CHAT = 0x16;

    /**
     * 数据统计
     */
    public static final Integer DATA_STATIS = 0x32;

    /**
     * 客服基本操作
     * 站点切换，上线，离线，暂停，转接
     */
    public static final Integer AGENT_MENU = 0x64;


//    FOLLOW = 0x04
//    COMMENT = 0x08
//    WRITE_ARTICLES = 0x16
//    MODERATE_COMMENTS = 0x32
//
//    MANAGEMENT = 0x64 # 管理后台
//    ADMINISTER = 0x80 # 管理网站

}
