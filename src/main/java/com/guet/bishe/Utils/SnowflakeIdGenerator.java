package com.guet.bishe.Utils;


public class SnowflakeIdGenerator {
    
    // 起始的时间戳 (2020-01-01 00:00:00)
    private static final long START_TIMESTAMP = 1577808000000L;
    
    // 每一部分占用的位数
    private static final long SEQUENCE_BIT = 12; // 序列号占用的位数
    private static final long MACHINE_BIT = 5;   // 机器标识占用的位数
    private static final long TIMESTAMP_BIT = 41; // 时间戳占用的位数
    
    // 每一部分的最大值
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    
    // 每一部分向左的位移
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    
    // 机器ID
    private long machineId;
    // 序列号
    private long sequence = 0L;
    // 上次生成ID的时间戳
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("Machine ID can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
        }
        this.machineId = machineId;
    }
    
    // 生成下一个ID
    public synchronized long nextId() {
        long timestamp = currentTime();
        
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }
        
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        
        lastTimestamp = timestamp;
        
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT) 
            | (machineId << MACHINE_LEFT) 
            | sequence;
    }
    
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    // 生成下一个ID，并转换为字符串类型
    public synchronized String nextIdAsString() {
        return String.valueOf(nextId());
    }
    
    private long currentTime() {
        return System.currentTimeMillis();
    }

}
