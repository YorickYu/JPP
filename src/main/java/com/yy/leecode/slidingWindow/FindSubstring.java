package com.yy.leecode.slidingWindow;


/**
 * 一个长字符串str，一个短字符串sub
 * 在str中寻找是否存在一个子串，满足sub所有字符相等，顺序无所谓
 * 如果有，返回子串的起始位置，如果没有返回-1
 */

public class FindSubstring{

    public static int find(String s, String a) {
        if (s==null || a==null || s.length()<a.length()) {
            return -1;
        }
        char[] aim = a.toCharArray();
        int[] count = new int[256];
        for (int i = 0; i < aim.length; i++) { // 记录 sub 字符串中的字符个数
            count[aim[i]]++;
        }
        int M = aim.length;
        char[] str = s.toCharArray();
        int inValidTimes = 0;
        int R = 0;
        for (; R < M; R++) { // 建立窗口
            if (count[str[R]]-- <= 0) {
                inValidTimes++;
            }
        }
        for (; R < str.length; R++) { // 滑动
            if (inValidTimes == 0) {
                return R - M;
            }
            if (count[str[R]]-- <= 0) { // 窗口扩容
                inValidTimes++;
            }
            if (count[str[R-M]]++ < 0) { // 窗口收缩
                inValidTimes--;
            }
        }
        return inValidTimes == 0 ? R - M : -1; // 判断最后一个窗口
    }

    public static void main(String[] args) {
        int i = find("abccasdf", "csa");
        System.out.println(i);
    }

}
