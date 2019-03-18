package com.canda.netty.g7.client.utils;


import java.net.InetSocketAddress;
import java.util.Objects;

import io.netty.channel.Channel;

/**
 * @author Wangkun
 * @since 2019/1/15 下午3:24
 */
public class ChannelUtils {

    public static String getHostString(Channel channel) {
        if (Objects.isNull(channel)) {
            return null;
        }
        return ((InetSocketAddress) channel.remoteAddress()).getHostString();
    }

    public static Integer getPort(Channel channel) {
        if (Objects.isNull(channel)) {
            return null;
        }
        return ((InetSocketAddress) channel.remoteAddress()).getPort();
    }

    public static String getAddress(Channel channel) {
        if (Objects.isNull(channel)) {
            return null;
        }
        return String.format("%s:%d", getHostString(channel), getPort(channel));
    }

}
