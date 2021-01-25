package com.yy.strategy;

public interface ConfigOrderStrategy<O> {
    /**
     * 下单策略
     */
    public Boolean orderStrategyInterface(O o);
}
