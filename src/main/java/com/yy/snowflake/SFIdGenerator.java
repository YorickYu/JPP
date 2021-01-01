package com.yy.snowflake;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SFIdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(SFIdGenerator.class);

    private long workId = 0; // 终端ID
    private long datacenterId = 0; // 数据中心ID
    /**
     * 参考文档
     * https://www.hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%94%AF%E4%B8%80ID%E5%B7%A5%E5%85%B7-IdUtil?id=snowflake
     */
    private Snowflake snowflake = IdUtil.createSnowflake(workId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            String host = NetUtil.getLocalhostStr();
            String hostName = NetUtil.getLocalHostName();
            workId = NetUtil.ipv4ToLong(host);
            logger.info("启动机器:", hostName, "@IP:", host);
        }catch (Exception e) {
            workId = NetUtil.getLocalhostStr().hashCode();
            logger.warn("机器状态异常:", e);
        }
    }

    /**
     * 获取雪花算法ID
     * @return 趋势递增的ID
     */
    public synchronized long getId() {
        return snowflake.nextId();
    }

    /**
     * ⚠️注意：这个方法不推荐高频调用
     * 自定义获取雪花算法ID
     * @param workId 终端ID
     * @param datacenterId 数据中心ID
     * @return 趋势递增的ID
     */
    public synchronized long getCustomizeId(long workId, long datacenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workId, datacenterId);
        if (snowflake == null)
            snowflake = IdUtil.createSnowflake(workId, datacenterId);
        return snowflake.nextId();
    }
}