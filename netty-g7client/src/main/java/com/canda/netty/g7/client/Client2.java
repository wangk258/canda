package com.canda.netty.g7.client;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.canda.netty.g7.client.enums.TargetAddressEnum;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Wangkun
 * @since 2018/12/21 上午10:43
 */
@Slf4j
public class Client2 extends AbstractClient {

    @Override
    protected void send(Channel channel) throws Exception {
        // 给服务器发消息
        sendByFile(channel, "/work/message/fftp/msg.log", 100);
//        sendByFile(channel, "/work/message/gnns/0200.msg", 100);
//        sendByFile(channel, "/work/message/smart/0500.txt", 100);
//        sendByFile(channel, "/work/message/gateway/bigMsg.log", 200);
//        sendByFile(channel, "/work/message/gateway/decoder.log", 100);
    }

    public static void main(String[] args) {
         new Client2().start(new InetSocketAddress("127.0.0.1", 2946));
//        new Client2().start(new InetSocketAddress("127.0.0.1", 1234));
     }

}
