package com.yao0er4.springcloud.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {
    public static void main(String[] args) {

        ZonedDateTime now = ZonedDateTime.now();
//        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("America/New_York")); // 获取指定时区的当前时间
        System.out.println(now); // 2022-08-08T07:55:15.958+08:00[Asia/Shanghai]

    }
}
