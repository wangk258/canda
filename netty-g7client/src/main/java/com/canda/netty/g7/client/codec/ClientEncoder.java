package com.canda.netty.g7.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Wangkun
 * @since 2018/12/13 下午4:03
 */
public class ClientEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        out.writeBytes((ByteBuf)msg);
    }

}
