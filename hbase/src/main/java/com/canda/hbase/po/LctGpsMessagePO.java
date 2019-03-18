package com.canda.hbase.po;

import java.io.Serializable;
import java.util.Date;

import com.chinaway.columnar.annotation.Column;
import com.chinaway.columnar.annotation.RowKey;
import com.chinaway.columnar.annotation.Table;

import lombok.Data;

/**
 * 消息中心Hbase持久化对象
 * 
 * @author Wangkun
 * @since 2019/2/27 4:12 PM
 */
@Table(name = "lct_gps_message")
@Data
public class LctGpsMessagePO implements Serializable {

    private static final long serialVersionUID = -3394899746487952959L;

    /**
     * Mysql中的ID_IMEI,ex: 1_107015291293119
     */
    @RowKey
    private String rowKey;

    /**
     * 消息中心Mysql中的id
     */
    private Long id;

    @Column(family = "f")
    private String imei;

    /**
     * 交互中心内部使用id，唯一值
     */
    @Column(family = "f")
    private String innerId;

    /**
     * 协议命令id
     */
    @Column(family = "f")
    private String cmd;

    /**
     * 类型：0/发送、1/接收、9/设置终端
     */
    @Column(family = "f")
    private int types;

    /**
     * 下发内容
     */
    @Column(family = "f")
    private String content;

    /**
     * 设备应答内容
     */
    @Column(family = "f")
    private String response;

    /**
     * 创建时间
     */
    @Column(family = "f")
    private Date createtime;

    /**
     * 最新发送时间
     */
    @Column(family = "f")
    private Date sendtime;

    /**
     * 最新更新时间-每次状态发生变化都会更新
     */
    @Column(family = "f")
    private Date updatetime;

    /**
     * <p>
     * 状态：0新消息,11重发消息准备状态,21mq推送成功,22dsp解析指令,23下发成功,24设备未在线,</br>
     * 80已结束消息超时,81已结束消息超过最大发送次数,82解析协议内容失败,90已完成收到设备应答,91已完成无需设备应答
     * </p>
     */
    @Column(family = "f")
    private int status;

    /**
     * 失败次数
     */
    @Column(family = "f")
    private int failcount;

    /**
     * 失败时，可重发发送次数
     */
    @Column(family = "f")
    private int counttimes;

    /**
     * 过期时间
     */
    @Column(family = "f")
    private Date expirationtime;

    /**
     * 优先级
     */
    @Column(family = "f")
    private int sort;

    /**
     * 附加条件
     */
    @Column(family = "f")
    private String addition;

    /**
     * 重发时间间隔
     */
    @Column(family = "f")
    private int intervaltime;

    /**
     * 定时发送时间
     */
    @Column(family = "f")
    private Date schedulertime;

    /**
     * 消息来源
     */
    @Column(family = "f")
    private String source;

}
