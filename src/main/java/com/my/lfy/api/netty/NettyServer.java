package com.my.lfy.api.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * NettyServer
 *
 * @author lfy
 * @date 2020/7/18
 **/
@Slf4j
@Component
public class NettyServer {

    @Value("${netty.port:null}")
    private Integer nettyPort;

    @PostConstruct
    public void start() throws Exception {
        log.info("启动记载Netty...");
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(nettyPort))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("ws", "webSocket", true
                                    , 65536 * 10));
                            socketChannel.pipeline().addLast(new MyWebSocketHander());
                        }
                    });
            log.info("启动加载netty2...");
            //服务器异步创建绑定
            ChannelFuture future = bootstrap.bind().sync();
            //关闭服务器通道
//            future.channel().closeFuture().sync();
            if (future.isSuccess()) {
                log.info("启动成功...");
            }
        } finally {
            //释放线程池资源
            work.shutdownGracefully().sync();
            boss.shutdownGracefully().sync();
        }
    }
}
