package controller;

import lombok.Data;

/**
 * @author liaojianfeng
 * @since 2020-07-30
 */
@Data
public class ResponseResult {

    /**
     * 返回状态码200成功
     */
    public int code;

    /**
     * 返回描述信息
     */
    private String msg;

    /**
     * 返回内容数据体
     */
    private Object data;

    public ResponseResult(Object data) {
        this.data = data;
    }

}
