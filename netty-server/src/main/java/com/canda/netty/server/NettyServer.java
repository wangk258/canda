package com.canda.netty.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Wangkun
 * @since 2019/5/5 3:00 PM
 */
@Slf4j
@Component
public class NettyServer {

    private static final int BOSS_GROUP_THREADS = 4;

    private static final int WORKER_GROUP_THREADS = 100;

    /**
     * 限制单次read buffer的最大size，防止buffer太大导致内存溢出，64KB
     */
    private static final int MAX_READ_BUFFER_SIZE = 64 * 1024;

    /**
     * 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50
     */
    private static final int SO_BACKLOG = 1024;

    @Autowired
    // private GnnsServerHandler gnnsServerHandler;

    @PostConstruct
    public void doStart() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(BOSS_GROUP_THREADS);
        EventLoopGroup workerGroup = new NioEventLoopGroup(WORKER_GROUP_THREADS);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)).option(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                // .option(ChannelOption.SO_BACKLOG, SO_BACKLOG)
                .option(ChannelOption.SO_RCVBUF, MAX_READ_BUFFER_SIZE)
                // .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                // .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) {
                        /**
                         * 入站事件在ChannelPipeline双向链表中由头到尾正向传播，出站事件则方向相反
                         */
                        // channel.pipeline().addLast("decoder",
                        // new DelimiterBasedFrameDecoder(CodecConstants.MAX_MSG_LENGTH, Boolean.TRUE,
                        // Boolean.TRUE, CodecConstants.MSG_DELIMITER_BYTEBUF));
                        // // channel.pipeline().addLast("decoder", new GnnsDecoder(Boolean.TRUE));
                        // channel.pipeline().addLast("encoder", new GnnsEncoder(Boolean.FALSE));
                        // channel.pipeline().addLast("gnnsServerHandler", gnnsServerHandler);
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
//                                ReferenceCountUtil.release(msg);
                            }
                        });
                    }
                });
            ChannelFuture channelFuture = serverBootstrap.bind(16531).sync();
            log.info("部标代理服务器启动成功，绑定端口：{}", 16531);
            // 异步监听服务端close事件
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("分路网关部标服务器启动异常: {}", e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
