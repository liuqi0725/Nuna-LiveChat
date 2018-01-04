package com.liuqi.nuna.module.role;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:52 2018/1/4
 */
public class Roles {

    /**
     * 未认证邮箱用户
     */
    public static final Object[] CHAT_UNCONFIRMED_USER = new Object[]{
            "CHAT_UNCONFIRMED_USER",
            0,
            true,
            "未认证邮箱用户"
    };

    /**
     * 管理员
     * 全部权限
     */
    public static final Object[] CHAT_ADMIN_USER = new Object[]{ "CHAT_ADMIN_USER", 0xff,false,"管理员"};

    /**
     * 管理员助手
     * 不能实时聊天
     */
    public static final Object[] CHAT_ASSIST_ADMIN_USER = new Object[]{
            "CHAT_ASSIST_ADMIN_USER",
            Permission.CHANGE_USER_INFO | Permission.SITE_MANAGE | Permission.DASHBOARD |
                    Permission.USER_MANAGE | Permission.DATA_STATIS,
            false,
            "管理员助手"
    };

    /**
     * 客服
     * 只能实时对话
     */
    public static final Object[] CHAT_AGENT = new Object[]{
            "CHAT_AGENT",
            Permission.AGENT_MENU | Permission.CHANGE_USER_INFO | Permission.LIVE_CHAT | Permission.DATA_STATIS,
            false,
            "管理员"
    };


    public static void main(String[] args) {

        String sql = "insert into nuna_chat_user_role(`name`,`permissions`,`user_default`,`desc`,`created_at`) values('%s',%s,%s,'%s',NOW());";


        System.out.println(String.format(sql,Roles.CHAT_UNCONFIRMED_USER[0],Roles.CHAT_UNCONFIRMED_USER[1],String.valueOf((Boolean)Roles.CHAT_UNCONFIRMED_USER[2] ? 0 : 1),Roles.CHAT_UNCONFIRMED_USER[3]));
        System.out.println(String.format(sql,Roles.CHAT_ADMIN_USER[0],Roles.CHAT_ADMIN_USER[1],String.valueOf((Boolean)Roles.CHAT_ADMIN_USER[2] ? 0 : 1),Roles.CHAT_ADMIN_USER[3]));
        System.out.println(String.format(sql,Roles.CHAT_AGENT[0],Roles.CHAT_AGENT[1],String.valueOf((Boolean)Roles.CHAT_AGENT[2] ? 0 : 1),Roles.CHAT_AGENT[3]));
        System.out.println(String.format(sql,Roles.CHAT_ASSIST_ADMIN_USER[0],Roles.CHAT_ASSIST_ADMIN_USER[1],String.valueOf((Boolean)Roles.CHAT_ASSIST_ADMIN_USER[2] ? 0 : 1),Roles.CHAT_ASSIST_ADMIN_USER[3]));

    }


}
