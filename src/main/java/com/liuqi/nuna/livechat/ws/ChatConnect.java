package com.liuqi.nuna.livechat.ws;

import com.alibaba.fastjson.JSONObject;
import com.liuqi.nuna.livechat.other.tool.HTMLFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 上午10:25 2018/1/3
 */
@ServerEndpoint(value = "/nunachat/connect/{username}/{userphone}")
public class ChatConnect {

    private static final Logger log = LoggerFactory.getLogger(ChatConnect.class);

    private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);

    private static final CopyOnWriteArraySet<ChatConnect> connections = new CopyOnWriteArraySet<ChatConnect>();

    private static final Map<String , ChatConnect> onLineUser = new HashMap<String , ChatConnect>();


    private final String nickname;
    private Session session;

    public ChatConnect() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }


    @OnOpen
    public void start(@PathParam("username") String username,
                      @PathParam("userphone") String userphone,
                      Session session) {
        this.session = session;
        connections.add(this);

        String message = String.format("* 当前在线人数为 %s，%s %s", onLineUser.size(),username, "has joined.");
//        JSONObject obj = new JSONObject();
//        obj.put("success",true);
//        obj.put("msg",message);
//        renderObject(obj);
        broadcast(message);
    }


    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s",
                nickname, "has disconnected.");
        broadcast(message);
    }


    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s",
                nickname, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }

    private void renderObject(JSONObject obj) {

        try {
            this.session.getBasicRemote().sendObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

    }

    private void broadcast(String msg) {

        try {
            this.session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for (ChatConnect client : connections) {
//            try {
//
//                synchronized (client) {
//
//                    this.session.getBasicRemote().sendText(msg);
//
//                    if(client.session == this)
//                    client.session.getBasicRemote().sendText(msg);
//                }
//            } catch (IOException e) {
//                log.debug("Chat Error: Failed to send message to client", e);
//                connections.remove(client);
//                try {
//                    client.session.close();
//                } catch (IOException e1) {
//                    // Ignore
//                }
//                String message = String.format("* %s %s",
//                        client.nickname, "has been disconnected.");
//                broadcast(message);
//            }
//        }
    }
}