package com.my.lfy.config.websocket;

import com.my.lfy.utils.WebSocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static com.my.lfy.utils.WebSocketUtils.*;

/**
 * 因为WebSocket是类似客户端服务端的形式(采用ws协议)，那么这里的WebSocketServer其实就相当于一个ws协议的Controller
 * 直接@ServerEndpoint("/websocket")@Component启用即可，然后在里面实现@OnOpen,@onClose,@onMessage等方法
 *
 * @author lfy
 * @date 2020/6/28
 **/
@Slf4j
@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocketServer {


    @PostConstruct
    public void init() {
        log.info("webSocket 加载...");
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        log.info("建立连接成功...");
        SESSION_POOL.put(userName, session);
        //在线人数加1
        WebSocketUtils.addOnlineCount();
        log.info("{}加入webSocket!当前人数为:{}.", userName, ONLINE_NUM);
        log.info("欢迎用户[{}]来到聊天室...", userName);
        sendMessageAll("欢迎用户[" + userName + "] 来到聊天室！");
    }

    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        SESSION_POOL.remove(userName);
        WebSocketUtils.subOnlineCount();
        log.info("{}断开webSocket连接!当前人数为;{}.", userName, ONLINE_NUM);
        sendMessageAll("用户[" + userName + "] 离开聊天室了！");
    }


    @OnMessage
    public void onMessage(@PathParam(value = "sid") String userName, String message) {
        message = "客户端" + message + ",已收到";
        log.info("message={}.", message);
        sendMessageAll("用户[" + userName + "] : " + message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        log.error("发生错误...");
        session.close();
        log.error("[ERROR]=========>", throwable);
    }
}
