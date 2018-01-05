package com.liuqi.nuna.livechat.other.sys;

import com.alibaba.fastjson.JSONObject;
import com.liuqi.nuna.livechat.other.handler.SpringContextHandler;
import com.liuqi.nuna.livechat.pojo.ChatSetting;
import com.liuqi.nuna.livechat.service.ChatSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:13 2018/1/3
 */
public class AppEnvInit {

    private static final Logger logger = LoggerFactory.getLogger(AppEnvInit.class);

    public static void Init(){
        //启动队列
        startQueue();
        //读取数据库配置
        readDataBaseParams();
    }

    private static void startQueue(){
        logger.info("Start TaskPool & Queue...");

        logger.info("Start TaskPool & Queue. End.");
    }

    private static void readDataBaseParams(){

        logger.info("Read DataBase Params...");

        //读取数据库参数
        ChatSettingService service = SpringContextHandler.getSpringContext().getBean(ChatSettingService.class);

        List<ChatSetting> settings = service.getChatSettingWithNormal();

        for(ChatSetting setting : settings){

            if(setting.getKey_name().equals("AGENT_SAME_TIME_SERVICE_NUMBER")){
                SystemParams.AGENT_SAME_TIME_SERVICE_NUMBER = Integer.parseInt(setting.getKey_value());
                logger.info("Read DataBase Params [AGENT_SAME_TIME_SERVICE_NUMBER]. >> [{}]",SystemParams.AGENT_SAME_TIME_SERVICE_NUMBER);

                continue;
            }

            if(setting.getKey_name().equals("CHAT_MAX_QUEUE_NUMBER")){
                SystemParams.CHAT_MAX_QUEUE_NUMBER = Integer.parseInt(setting.getKey_value());
                logger.info("Read DataBase Params [CHAT_MAX_QUEUE_NUMBER]. >> [{}]",SystemParams.CHAT_MAX_QUEUE_NUMBER);
            }

            if(setting.getKey_name().equals("CHAT_SESSIONG_AGENT_TIME_OUT")){
                SystemParams.CHAT_SESSIONG_AGENT_TIME_OUT = Integer.parseInt(setting.getKey_value());
                logger.info("Read DataBase Params [CHAT_SESSIONG_AGENT_TIME_OUT]. >> [{}]",SystemParams.CHAT_SESSIONG_AGENT_TIME_OUT);
            }

            if(setting.getKey_name().equals("CHAT_SESSIONG_CUSTOMER_TIME_OUT")){
                SystemParams.CHAT_SESSIONG_CUSTOMER_TIME_OUT = Integer.parseInt(setting.getKey_value());
                logger.info("Read DataBase Params [CHAT_SESSIONG_CUSTOMER_TIME_OUT]. >> [{}]",SystemParams.CHAT_SESSIONG_CUSTOMER_TIME_OUT);
            }

            if(setting.getKey_name().equals("CHAT_MAIL_SETTING")){
                SystemParams.CHAT_MAIL_SETTING = JSONObject.parseObject(setting.getKey_value());
                logger.info("Read DataBase Params [CHAT_MAIL_SETTING]. >> [{}]",SystemParams.CHAT_MAIL_SETTING.toString());
            }

            if(setting.getKey_name().equals("CHAT_USER_TOKEN_KEY")){
                SystemParams.CHAT_USER_TOKEN_KEY = setting.getKey_value();
                logger.info("Read DataBase Params [CHAT_USER_TOKEN_KEY]. >> [{}]",SystemParams.CHAT_USER_TOKEN_KEY);
            }

        }

        logger.info("Read DataBase Params. End.");

    }
}
