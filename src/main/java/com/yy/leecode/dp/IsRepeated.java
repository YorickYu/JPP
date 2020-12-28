package com.yy.leecode.dp;

import java.util.HashSet;

/*
 字符种类相同，就算作一类
 计算：字符串数组arr中一共有多少类？
 */
public class IsRepeated {
    public int types(String[] arr) {
        HashSet<Integer> types = new HashSet<>();
        for (String str: arr) {
            char[] chars = str.toCharArray();
            int key = 0;
            // 用一个int类型的整数记录每一类字符串
            // 0000 1111
            // hgfe dcba
            for (int i = 0; i < chars.length; i++) {
                key |= 1 << (chars[i] - 'a'); // 'b' - 'a' = 1
            }
            types.add(key);
        }
        return types.size();
    }

    public static void main(String[] args) {
        int x = 'b' - 'a';
        System.out.println("x = " + x);

        int b = 'b';
        System.out.println(b);
    }
}
