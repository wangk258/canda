package com.canda.netty.g7.client.utils;

import java.util.Objects;

import com.canda.netty.g7.client.constants.CodecConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * 报文工具类
 * 
 * @author Wangkun
 * @since 2018/12/18 上午11:12
 */
public class MsgUtils {

    /**
     * 是否为错误的报文
     * 
     * @param bytes
     * @param minSize
     * @return
     */
    public static boolean isErrorMsg(byte[] bytes, int minSize) throws Exception {
        if (Objects.isNull(bytes) || bytes.length < minSize) {
            throw new Exception("报文长度有误");
        }
        if (bytes[0] != CodecConstants.MSG_DELIMITER || bytes[bytes.length - 1] != CodecConstants.MSG_DELIMITER) {
            throw new Exception("报文的标识符不合法");
        }
        return false;
    }

    /**
     * 格式化十六进制字符串，ex：7e00017e -> 7E 00 01 7E
     * 
     * @param hexStr
     * @return
     */
    public static String formatHexString(String hexStr) {
        if (StringUtils.isBlank(hexStr)) {
            return hexStr;
        }
        StringBuffer sb = new StringBuffer();
        char[] hexByte = hexStr.toCharArray();
        for (int i = 0; i < hexByte.length; i++) {
            sb.append((hexByte[i]));
            if ((i + 1) % 2 == 0) {
                sb.append(" ");
            }
        }
        return sb.toString().toUpperCase().trim();
    }

}
