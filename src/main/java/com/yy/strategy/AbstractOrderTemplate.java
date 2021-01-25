package com.yy.strategy;

import java.util.List;

/**
 * 下单模板
 * @param <C> CouponBean
 * @param <O> OrderBean
 */
public abstract class AbstractOrderTemplate<C, O> {
    /**
     * 筛选可用优惠券
     * @return
     */
    abstract List<C> filterCoupons(O o);

    /**
     * 根据券规则使用
     * @param ts
     * @return
     */
    abstract Boolean useCoupons(C...ts);

    /**
     * 订单入库
     * @param o
     */
    abstract void saveOrders(O o);
}
