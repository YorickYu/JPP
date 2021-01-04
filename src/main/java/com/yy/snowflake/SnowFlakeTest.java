package com.yy.snowflake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SnowFlakeTest {

    public static void main(String[] args) {
        SFIdGenerator sfIdGenerator = new SFIdGenerator();
        long id = sfIdGenerator.getId();
        System.out.println("id = " + id);
    }
}
