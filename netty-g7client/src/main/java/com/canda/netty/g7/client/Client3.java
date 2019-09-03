package com.canda.netty.g7.client;

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
public class Client3 extends AbstractClient {

    @Override
    protected void send(Channel channel) throws Exception {
        // 给服务器发消息
//        sendByFile(channel, "/work/message/fftp/msg.log", 100);
//        sendByFile(channel, "/work/message/gateway/0200.log", 100);
//        sendByFile(channel, "/work/message/gateway/bigMsg.log", 100);`
//        sendByFile(channel, "/work/message/gateway/decoder.log", 100);
//        sendByFile(channel, "/work/message/smart/0038.txt", 100);
        send(channel, "7E 02 00 00 32 06 48 04 30 27 87 0E 70 00 00 00 00 00 0C 00 03 01 DA 66 05 07 37 47 4E 00 07 00 D2 00 A2 19 07 05 17 26 23 01 04 00 02 59 DC 03 02 00 00 25 04 00 00 00 00 30 01 17 31 01 0E DE 7E");
//        send(channel, "7E 91 01 00 15 01 91 48 05 89 24 00 16 0D 34 37 2E 31 31 30 2E 31 39 38 2E 31 39 04 D3 00 00 01 00 01 1C 7E");

    }

    public static void main(String[] args) {
        // new Client1().start(new InetSocketAddress("172.22.34.228", 2946));
        new Client3().start(TargetAddressEnum.GATEWAY_LOCAL);
    }

}
