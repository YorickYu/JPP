package com.yy.strategy;
public class ConfigOrderContext {

    /**
     * 持有策略
     */
    private ConfigOrderStrategy strategy;
    /**
     * 模板
     */
    private AbstractOrderTemplate template;

    /**
     * 有参构造
     * @param strategy
     */
    public ConfigOrderContext(ConfigOrderStrategy strategy, AbstractOrderTemplate template) {
        this.strategy = strategy;
        this.template = template;
    }

    public ConfigOrderContext(ConfigOrderStrategy strategy) {
        this.strategy = (ConfigOrderStrategy) (this.template = (AbstractOrderTemplate) strategy);
    }

    /**
     * 调用策略方法
     */
    public void doExecute(Object o) {

        Boolean b1 = template.useCoupons(template.filterCoupons(o));
        if (b1 != null && b1) {
            Boolean b2 = strategy.orderStrategyInterface(o);
            if (b2 != null && b2) {
                template.saveOrders(o);
            }
        }

    }

}
