package com.nines.flowable.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * @author tanyujie
 * @classname MyIdGeneratorConfig
 * @description 自定义 JPA 雪花id生成器
 * @date 2022/7/20 9:09
 * @since 1.0
 */
@Slf4j
public class MyIdGeneratorConfig implements IdentifierGenerator {

    /**
     * 终端ID
     */
    public static long WORKER_ID = 1;

    /**
     * 数据中心id
     */
    public static long DATACENTER_ID = 1;

    private final Snowflake snowflake = IdUtil.getSnowflake(WORKER_ID, DATACENTER_ID);

    public synchronized String snowflakeId() {
        return snowflake.nextIdStr();
    }

    public synchronized String snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workerId, datacenterId);
        return snowflake.nextIdStr();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflakeId(WORKER_ID, DATACENTER_ID);
    }
}
