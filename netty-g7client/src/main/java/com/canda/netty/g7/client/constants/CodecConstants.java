package com.canda.netty.g7.client.constants;

/**
 * @author Wangkun
 * @since 2018/12/13 上午11:04
 */
public interface CodecConstants {

    /**
     * 报文标志位
     */
    byte MSG_DELIMITER = 0x7e;

    /**
     * 流水号最大值
     */
    int SERIAL_NO_MAX_VALUE = 65535;

    /**
     * 流水号初始值
     */
    int SERIAL_NO_INIT_VALUE = 1;

}
