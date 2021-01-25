package com.yy.strategy;

import java.util.List;

public class JDOrderStrategyImpl extends AbstractOrderTemplate implements ConfigOrderStrategy {
    @Override
    public Boolean orderStrategyInterface(Object o) {
        System.out.println("celue"+ o);
        return true;
    }

    @Override
    List filterCoupons(Object o) {
        System.out.println("filterCoupons");
        return null;
    }

    @Override
    Boolean useCoupons(Object[] objects) {
        System.out.println("filterCoupons");

        return true;
    }

    @Override
    void saveOrders(Object o) {
        System.out.println("filterCoupons");

    }

}
