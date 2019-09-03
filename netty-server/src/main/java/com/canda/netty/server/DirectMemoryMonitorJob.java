package com.canda.netty.server;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.google.common.util.concurrent.MoreExecutors;

import io.netty.util.internal.PlatformDependent;
import lombok.extern.slf4j.Slf4j;

/**
 * 堆外内存监控
 * 
 * @author Wangkun
 * @since 2019/4/26 11:25 AM
 */
@Slf4j
@Component
public class DirectMemoryMonitorJob {

    private AtomicLong directMemory = new AtomicLong();

    private static final int CORE = 1;

    private static final int TIME_OUT = 10;

    private ScheduledExecutorService executorService = MoreExecutors
        .getExitingScheduledExecutorService(new ScheduledThreadPoolExecutor(CORE), TIME_OUT, TimeUnit.SECONDS);

    public DirectMemoryMonitorJob() {
        Field field = ReflectionUtils.findField(PlatformDependent.class, "DIRECT_MEMORY_COUNTER");
        field.setAccessible(Boolean.TRUE);
        try {
            directMemory = (AtomicLong)field.get(PlatformDependent.class);
        } catch (IllegalAccessException e) {
            log.error("通过反射获取堆外内存使用计数器异常, msg: {}", e.getMessage(), e);
        }
    }

    @PostConstruct
    public void execute() {
        executorService.scheduleAtFixedRate(
            () -> log.info("最大堆外内存：{}， 已使用堆外内存大小：{}", PlatformDependent.maxDirectMemory(), directMemory.get()),
            BigDecimal.ZERO.intValue(), BigDecimal.ONE.intValue(), TimeUnit.SECONDS);
    }

}
