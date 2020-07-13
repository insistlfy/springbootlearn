package com.my.lfy.api.websocket.controller;

import com.my.lfy.config.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebsocketController
 *
 * @author lfy
 * @date 2020/6/28
 **/
@Slf4j
@RestController
@RequestMapping("/api/ws")
@Api(tags = "【WEBSOCKET-TEST】")
public class WebsocketController {

//    @GetMapping("/sendAll")
//    public String sendAllMessage(@RequestParam("message") String message) {
//        try {
//            WebSocketServer.sendMessage(,message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }

    @GetMapping("/sendOne")
    public String sendOneMessage(@RequestParam("message") String message,
                                 @RequestParam("username") String username) {
        WebSocketServer.sendInfo(username, message);
        return "ok";
    }
}
