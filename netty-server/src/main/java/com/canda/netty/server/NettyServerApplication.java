package com.canda.netty.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wangkun
 * @since 2019/5/5 3:11 PM
 */
@SpringBootApplication(scanBasePackages = {"com.canda.netty.server"})
public class NettyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

}
