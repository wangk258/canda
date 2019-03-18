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
public class Client1 extends AbstractClient {

    @Override
    protected void send(Channel channel) throws Exception {
        // 给服务器发消息
        sendByFile(channel, "/work/temp/fftp/msg.log", 100);
    }

    public static void main(String[] args) {
        // new Client1().start(new InetSocketAddress("172.22.34.228", 2946));
        new Client1().start(TargetAddressEnum.FFTP_TEST);
    }

}
