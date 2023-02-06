package com.yy.biset;

import java.util.BitSet;

/**
 * @className: bitsetTest
 * @description:
 * @author: yloopdaed
 * @date: 2022/1/1
 **/
public class bitsetTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(65);


        Long num = 17721182801L;
        System.out.println(1L<<17721182801L);

        System.out.println(Long.toBinaryString(1L<<17721182801L));


        boolean b = (1 << 62) == (1 << 30);
        System.out.println(b);



    }
}
