package com.sherlock.mallcommon.utils;

/**
 * Copyright (C), 2015-2018,
 * FileName: IDMaker
 * Author:   jcj
 * Date:     2018/9/15 10:46
 * Description: 雪花算法 生成唯一ID
 */
public class IDMaker {
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    //Thu, 04 Nov 2010 01:42:54 GMT
    private long twepoch = 1288834974657L;
    //节点ID长度
    private long workerIdBits = 5L;
    //数据中心ID长度
    private long dataCenterIdBits = 5L;
    //最大支持机器节点数0~31，一共32个
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    //最大支持数据中心节点数0~31，一共32个
    private long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    //序列号12位
    private long sequenceBits = 12L;
    //机器节点左移12位
    private long workerIdShift = sequenceBits;
    //数据中心节点左移17位
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    //时间毫秒数左移22位
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    //4095
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;

    private static class IDMakerHolder {
        private static final IDMaker instance = new IDMaker();
    }

    public static IDMaker get() {
        return IDMakerHolder.instance;
    }

    public IDMaker() {
        this(0L, 0L);
    }

    public IDMaker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDataCenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 功能描述: <br> 生产唯一性id
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/18 17:24
     */
    public synchronized String nextId() {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                //自旋等待到下一毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L; //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID。
        // 000000000000000000000000000000000000000000  00000            00000       000000000000
        // time                                                               datacenterId   workerId    sequence
        long result = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << dataCenterIdShift)
                | (workerId << workerIdShift) | sequence;
        return String.valueOf(result);
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
