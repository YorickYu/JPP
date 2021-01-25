package com.yy.strategy;

public class StrategyTest {

    public static void main(String[] args) {

        ConfigOrderContext context = new ConfigOrderContext(new JDOrderStrategyImpl());
        context.doExecute(new Object());

    }
}
