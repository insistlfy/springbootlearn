package com.my.lfy.utils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocketUtils
 *
 * @author lfy
 * @date 2020/7/13
 **/
public final class WebSocketUtils {

    /**
     * 静态变量,用来记录当前在线连接数.(线程安全)
     */
    public static AtomicInteger ONLINE_NUM = new AtomicInteger();

    /**
     * 存放每个客户端对应的WebSocketServer对象
     */
    public static ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();


    /**
     * 群发
     *
     * @param message String
     */
    public static void sendMessageAll(String message) {
        SESSION_POOL.forEach((sessionId, session) -> sendMessage(session, message));
    }

    /**
     * 发送给指定用户消息
     *
     * @param session 用户 session
     * @param message 发送内容
     */
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addOnlineCount() {
        WebSocketUtils.ONLINE_NUM.incrementAndGet();
    }

    public static void subOnlineCount() {
        WebSocketUtils.ONLINE_NUM.decrementAndGet();
    }
}
