package com.hithy.common.utils;

public class ApsUtil {
    private static SnowFlake snowFlake = new SnowFlake(1, 1);

    /**
     * 获取ID
     * @return
     */
    public static long id() {
        return snowFlake.nextId();
    }

}
