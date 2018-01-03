package com.liuqi.nuna.livechat.ws;

import com.liuqi.nuna.livechat.pojo.ChatSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * 会话管理
 *
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午3:01 2018/1/3
 */
public class ChatSessionHandler {

    /**
     * 所有的链接
     */
    private static CopyOnWriteArraySet<ChatConnect> connections = new CopyOnWriteArraySet<ChatConnect>();

    /**
     * 所有的会话
     */
    private static Vector<ChatSession> onLineUser = new HashMap<String , ChatConnect>();

}
