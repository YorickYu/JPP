package com.yy.emoji;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @className: EmojiPatternTest
 * @description:
 * @author: yloopdaed
 * @date: 2022/6/27
 **/
public class RoundTest {
    public static void main(String[] args) {

        BigDecimal a = new BigDecimal(57000);
        BigDecimal b = new BigDecimal("10000.0");
        BigDecimal c = a.divide(b, 1, RoundingMode.DOWN);
        float f = c.floatValue();
        int scale = 1;
        BigDecimal b0 = new BigDecimal(f);
        BigDecimal b1 = new BigDecimal(f);
        BigDecimal b2 = new BigDecimal(f);
        BigDecimal b3 = new BigDecimal(f);
        BigDecimal b4 = new BigDecimal(f);
        BigDecimal b5 = new BigDecimal(f);
        BigDecimal b6 = new BigDecimal(f);
        BigDecimal b7 = new BigDecimal("10.2345");

        double f0 = c.setScale(scale, RoundingMode.UP).doubleValue();
        double f1 = c.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
        double f2 = c.setScale(scale, BigDecimal.ROUND_CEILING).doubleValue();
        double f3 = c.setScale(scale, BigDecimal.ROUND_FLOOR).doubleValue();
        double f4 = c.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        double f5 = c.setScale(scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        double f6 = c.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        double f7 = c.setScale(4, BigDecimal.ROUND_UNNECESSARY).doubleValue();

        System.out.println(f + "使用 远离零方向舍入（ROUND_UP）方式四舍五入结果为：" + f0);
        System.out.println(f + "使用 趋向零方向舍入（ROUND_DOWN）方式四舍五入结果为：" + f1);
        System.out.println(f + "使用 向正无穷方向舍入（ROUND_CEILING）方式四舍五入结果为：" + f2);
        System.out.println(f + "使用 向负无穷方向舍入（ROUND_FLOOR）方式四舍五入结果为：" + f3);
        System.out.println(f + "使用 最近数字舍入(5进)（ROUND_HALF_UP）方式四舍五入结果为：" + f4);
        System.out.println(f + "使用 最近数字舍入(5舍)（ROUND_HALF_DOWN）方式四舍五入结果为：" + f5);
        System.out.println(f + "使用 银行家舍入法（ROUND_HALF_EVEN）方式四舍五入结果为：" + f6);
        //System.out.println(f + "使用 不需要舍入模式（ROUND_UNNECESSARY）方式结果为：" + f7);

    }
}
