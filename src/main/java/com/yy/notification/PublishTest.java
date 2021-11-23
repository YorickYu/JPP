package com.yy.notification;

/**
 * @className: PublishTest
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class PublishTest {
    public static void main(String[] args) {
        Publisher<String> publisher = new Publisher<>();
        publisher.record(msg -> {
            if (msg.equals("hahah")) {
                System.out.println("消费到咯 hahah");
            }
        });

        publisher.record(msg -> {
            if (msg.equals("hehe")) {
                System.out.println("消费到咯 hehe");
            }
        });

        publisher.publish("hehe");
    }
}
