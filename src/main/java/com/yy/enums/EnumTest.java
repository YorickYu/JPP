package com.yy.enums;

public class EnumTest {
    public static void main(String[] args) {
        System.out.println("CoopEnum.SEASON.SPRING.getKey() = " + CoopEnum.SEASON.SPRING.getKey());
        System.out.println("CoopEnum.SEASON.SPRING.getKey() = " + CoopEnum.SEASON.SPRING.getValue());

        System.out.println("CoopEnum.WEEK.MONDAY.getKey() = " + CoopEnum.WEEK.MONDAY.getKey());

        CoopEnum.SingletonEnum.INSTANCE.run();
    }
}
