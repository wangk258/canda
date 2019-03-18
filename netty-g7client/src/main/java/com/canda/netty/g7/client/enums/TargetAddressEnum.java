package com.canda.netty.g7.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wangkun
 * @since 2019/3/18 5:50 PM
 */
@AllArgsConstructor
@Getter
public enum TargetAddressEnum {

    FFTP_LOCAL("127.0.0.1", 16533),
    FFTP_TEST("172.22.35.223", 16533),

    SMART_LOCAL("127.0.0.1", 16531),
    ;

    private final String ip;

    private final int port;

}
