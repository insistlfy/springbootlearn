package com.my.lfy.api.websocket.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.my.lfy.utils.WebSocketUtils.SESSION_POOL;
import static com.my.lfy.utils.WebSocketUtils.sendMessage;

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

    @GetMapping("{sender}/to/{receiver}")
    public void sendOneMessage(@PathVariable("sender") String sender,
                               @PathVariable("receiver") String receiver,
                               @RequestParam("message") String message) {
        sendMessage(SESSION_POOL.get(receiver), "[" + sender + "]" + "-> [" + receiver + "] : " + message);

    }
}
