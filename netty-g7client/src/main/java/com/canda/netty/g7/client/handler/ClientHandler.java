package com.canda.netty.g7.client.handler;

import com.canda.netty.g7.client.utils.MsgUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("recivied: {}", MsgUtils.formatHexString(ByteBufUtil.hexDump((ByteBuf)msg)));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // Channel channel = ctx.channel();
        // channel.writeAndFlush(channel.alloc().buffer().writeBytes(AbstractClient.HexString2Bytes(
        // "7E 02 00 00 4F 06 46 38 30 35 24 00 D2 00 00 00 00 00 0C 24 03 01 C8 0B F1 07 46 FF A1 00 2C 01
        // 36 00 C7 18
        // 12 25 10 55 59 01 04 00 10 3F A4 02 02 00 00 03 02 00 00 25 04 00 00 00 10 2A 02 00 00 2B 04 00
        // 1C 00 00 30
        // 01 07 31 01 07 32 01 50 09 0A 00 00 00 00 00 00 00 00 00 00 EA 7E")));
        // channel.writeAndFlush(channel.alloc().buffer().writeBytes(AbstractClient.HexString2Bytes(
        // ("7E 01 04 23 02 06 46 38 30 35 24 0D 53 00 02 00 01 00 03 61 00 00 00 01 04 00 00 00 3C 00 00 00
        // 02 04 00 00
        // 00 0A 00 00 00 03 04 00 00 00 03 00 00 00 04 04 00 00 00 0A 00 00 00 05 04 00 00 00 03 00 00 00
        // 06 04 00 00
        // 00 0A 00 00 00 07 04 00 00 00 03 00 00 00 10 05 43 4D 4E 45 54 00 00 00 11 00 00 00 00 12 00 00
        // 00 00 13 0F
        // 31 38 32 2E 31 35 30 2E 30 32 37 2E 31 34 36 00 00 00 14 05 "
        // + "43 4D 4E 45 54 00 00 00 15 00 00 00 00 16 00 00 00 00 17 0C 36 30 2E 32 38 2E 31 39 35 2E 35
        // 34 00 00 00
        // 18 04 00 00 0B 82 00 00 00 19 04 00 00 03 B2 00 00 00 1A 0D 32 30 32 2E 39 36 2E 34 32 2E 31 31
        // 33 00 00 00
        // 1B 04 00 00 2A F9 00 00 00 1C 04 00 00 2A FA 00 00 00 1D 0D 32 30 32 2E 39 36 2E 34 32 2E 31 31
        // 33 00 00 00
        // 20 04 00 00 00 00 00 00 00 21 04 00 00 00 00 00 00 00 22 04 00 00 00 3C 00 00 00 27 04 00 00 01
        // 2C 00 00 00
        // 28 04 00 00 00 05 00 00 00 29 04 00 00 00 0A 00 00 00 2C 04 00 00 03 E8 00 00 00 2D 04 00 00 03
        // E8 00 00 00
        // 2E 04 00 00 03 E8 00 00 00 2F 04 00 00 03 E8 00 00 00 30 04 00 00 00 1E 00 00 00 31 02 00 00 00
        // 00 00 40 00
        // 00 00 00 41 0B 31 33 38 38 38 38 38 38 38 38 38 00 00 00 42 00 00 00 00 43 00 00 00 00 44 00 00
        // 00 00 45 04
        // 00 00 00 02 00 "
        // + "00 00 46 04 00 00 04 B0 00 00 00 47 04 FF FF FF FF 00 00 00 48 0B 31 33 38 38 38 38 38 38 38
        // 38 38 00 00
        // 00 49 00 00 00 00 50 04 08 00 00 10 00 00 00 51 04 00 00 00 00 00 00 00 52 04 1C 34 00 0F 00 00
        // 00 53 04 FF
        // FF FF FF 00 00 00 54 04 08 00 00 00 00 00 00 55 04 00 00 00 64 00 00 00 56 04 00 00 00 03 00 00
        // 00 57 04 00
        // 00 00 00 00 00 00 58 04 00 00 00 00 00 00 00 59 04 00 00 04 B0 00 00 00 5A 04 00 00 00 00 00 00
        // 00 5B 02 00
        // 64 00 00 00 5C 02 07 08 00 00 00 5D 02 46 FA 00 00 00 5E 02 00 00 00 00 00 64 04 00 00 00 00 00
        // 00 00 65 04
        // 00 00 00 00 00 00 00 70 04 00 00 00 00 00 00 00 71 04 00 00 00 00 00 00 00 72 04 00 00 00 00 00
        // 00 00 73 04
        // 00 00 00 00 00 00 00 74 04 00 00 00 00 00 00 00 80 04 00 00 06 0B 00 00 00 81 02 00 00 00 00 00
        // 82 02 00 00
        // 00 00 00 83 08 D7 D4 41 38 39 38 39 38 00 00 00 84 01 02 00 00 00 90 01 02 00 00 00 91 01 01 00
        // 00 00 92 01
        // 01 00 00 00 93 04 00 00 00 01 00 00 01 00 04 00 00 00 00 00 00 01 01 02 00 1E 00 00 01 02 04 00
        // 00 00 00 00
        // 00 01 03 02 00 1E 00 00 08 10 04 00 00 00 00 00 00 08 11 04 FF FF FF FF 00 00 E0 13 0E 6A 74 31
        // 2E 67 67 68
        // 79 70 74 2E 6E 65 74 00 00 E0 18 04 00 00 1B 60 00 00 E0 19 04 00 00 1B 60 00 00 E0 20 0E 6A 74
        // 32 2E 67 67
        // 68 79 70 74 2E 6E 65 74 11 7E"))));
    }

    // 与服务器建立连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
        super.channelActive(ctx);
    }

    // 与服务器断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive");
        super.channelInactive(ctx);
    }

    // 异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭管道
        ctx.channel().close();
        // 打印异常信息
        cause.printStackTrace();
    }

}
