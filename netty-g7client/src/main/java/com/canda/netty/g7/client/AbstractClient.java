package com.canda.netty.g7.client;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.canda.netty.g7.client.codec.ClientEncoder;
import com.canda.netty.g7.client.enums.TargetAddressEnum;
import com.canda.netty.g7.client.handler.ClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractClient {

    public void start(TargetAddressEnum targetAddressEnum) {
        start(targetAddressEnum.getIp(), targetAddressEnum.getPort());
    }

    public void start(String ip, int port) {
        start(new InetSocketAddress(ip, port));
    }

    public void start(InetSocketAddress address) {
        log.info("准备连接netty客户端，ip：{}, port：{} ...", address.getHostString(), address.getPort());
        // worker负责读写数据
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            // 辅助启动类
            Bootstrap bootstrap = new Bootstrap();

            // 设置线程池
            bootstrap.group(worker);

            // 设置socket工厂
            bootstrap.channel(NioSocketChannel.class);

            // 设置管道
            bootstrap.handler(new ChannelInitializer() {

                @Override
                protected void initChannel(Channel ch) {
                    // 获取管道
                    ChannelPipeline pipeline = ch.pipeline();
                    // 字符串解码器
                    pipeline.addLast(new ClientEncoder());
                    // 字符串编码器
                    pipeline.addLast(new StringEncoder());
                    // 处理类
                    pipeline.addLast(new ClientHandler());
                }

            });

            // 发起异步连接操作
            ChannelFuture futrue = bootstrap.connect(address).sync();

            new Thread(() -> {
                try {
                    send(futrue.channel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // 等待客户端链路关闭
            futrue.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅的退出，释放NIO线程组
            worker.shutdownGracefully();
        }
    }

    protected abstract void send(Channel channel) throws Exception;

    public static byte[] HexString2Bytes(String hexStr) {
        hexStr = hexStr.replace(" ", "");
        byte[] b = new byte[hexStr.length() / 2];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte)Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
        }
        return b;
    }

    protected void send(Channel channel, String hexStr) {
        channel.writeAndFlush(channel.alloc().buffer().writeBytes(HexString2Bytes(hexStr)));
    }

    protected void sendByFile(Channel channel, String filePath, long sleep) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        if (CollectionUtils.isEmpty(lines)) {
            log.warn("未从文件中读取到报文数据");
            return;
        }
        log.info("开始发送 >>>>>>");
        for (String str : lines) {
            channel.writeAndFlush(channel.alloc().buffer().writeBytes(HexString2Bytes(str.trim())));
            if (sleep > 0) {
                Thread.sleep(sleep);
            }
        }
        log.info("发送完毕 !!!!!!");
    }
}