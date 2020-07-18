package com.my.lfy.api.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * MyWebSocketHander
 *
 * @author lfy
 * @date 2020/7/18
 **/
@Slf4j
public class MyWebSocketHander extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    @ApiOperation(value = "客户端与服务端建立连接时触发")
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("与客户端建立连接,通道开启...");
        channelGroup.add(ctx.channel());
    }

    @Override
    @ApiOperation(value = "客户端与服务端关闭连接时触发")
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("与客户端断开连接,通道关闭...");
        channelGroup.remove(ctx.channel());
    }

    @Override
    @ApiOperation(value = "服务器接受客户端的数据信息")
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) {
        log.info("服务器收到数据 : [{}].", msg.text());
        sendAllMessage();
    }

    /**
     * 给固定的人发消息
     *
     * @param ctx ChannelHandlerContext
     */
    private void sendMessage(ChannelHandlerContext ctx) {
        String msg = "Hello," + ctx.channel().localAddress() + "send message..";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(msg));
    }

    /**
     * 发送群消息
     */
    private void sendAllMessage() {
        String msg = "I am Server ,sending...";
        channelGroup.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
