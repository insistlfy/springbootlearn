package com.my.lfy.api.websocket.controller;

import com.my.lfy.config.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * WebsocketController
 *
 * @author lfy
 * @date 2020/6/28
 **/
@Controller
@Api(tags = "【WEBSOCKET-TEST】")
public class WebsocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        return new ModelAndView("/webSocket");
    }
}
