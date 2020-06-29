package com.my.lfy.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocketServer
 *
 * @author lfy
 * @date 2020/6/28
 **/
@Slf4j
@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocketServer {

    /**
     * 静态变量,用来记录当前在线连接数.(线程安全)
     */
    private static AtomicInteger ONLINE_NUM = new AtomicInteger();

    /**
     * 存放每个客户端对应的WebSocketServer对象
     */
    private static ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 发送消息
     *
     * @param session Session
     * @param message String
     * @throws IOException e
     */
    public void sendMessage(Session session, String message) throws IOException {
        if (null != session) {
            synchronized (session) {
                log.info("发送数据====>[{}].", message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 给指定用户发送消息
     *
     * @param userName String
     * @param message  String
     */
    public void sendInfo(String userName, String message) {
        Session session = SESSION_POOL.get(userName);
        try {
            sendMessage(session, message);
        } catch (IOException e) {
            log.error("发送数据失败");
            log.error("发送数据失败======>", e);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        log.info("建立连接成功...");
        SESSION_POOL.put(userName, session);
        addOnlineCount();
        log.info("{}加入webSocket!当前人数为:{}.", userName, ONLINE_NUM);
        try {
            sendMessage(session, "欢迎" + userName + "加入连接!");
        } catch (IOException e) {
            log.error("onOpen===>sendMessage======>", e);
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        SESSION_POOL.remove(userName);
        subOnlineCount();
        log.info("{}断开webSocket连接!当前人数为;{}.", userName, ONLINE_NUM);
    }


    @OnMessage
    public void onMessage(String message) {
        message = "客户端" + message + ",已收到";
        log.info("message={}.", message);
        for (Map.Entry<String, Session> entry : SESSION_POOL.entrySet()) {
            try {
                sendMessage(entry.getValue(), message);
            } catch (IOException e) {
                log.error("onMessage===>sendMessage==+>", e);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发生错误...");
        log.error("[ERROR]=========>", throwable);
    }

    private static void addOnlineCount() {
        ONLINE_NUM.incrementAndGet();
    }

    private static void subOnlineCount() {
        ONLINE_NUM.decrementAndGet();
    }
}
