package com.yy.leecode;


import java.util.HashMap;
import java.util.Iterator;

public class ReverseWords {
    // 1 <= s.length <= 104
    // s 包含英文大小写字母、数字和空格 ' '
    // s 中 至少存在一个 单词
    public static String reverseWords(String s) {
        String target = "";
        String[] strings = s.split(" ");
        HashMap m = new HashMap();
        int modCount = 0;

        for (int i = 0; i < strings.length; i++) {
            String innerstring = strings[i];
            if (!innerstring.equals("")) {
                m.put(modCount++, innerstring);
            }
        }

        for (;modCount>0;) {
            target += m.get(--modCount);
            if (modCount != 0)
                target += " ";
        }

        return target;
    }

    public static String reverseWords2(String s) {
        StringBuffer sb = new StringBuffer();
        String[] strings = s.split("\\s+");
        HashMap m = new HashMap();
        int modCount = 0;

        for (int i = 0; i < strings.length; i++) {
            String innerstring = strings[i];
            if (!innerstring.equals("")) {
                m.put(modCount++, innerstring);
            }
        }

        for (;modCount>0;) {
            sb.append(m.get(--modCount));
            if (modCount != 0)
                sb.append(" ");
        }

        return sb.toString();
    }

    public static String reverseWords3(String s) {
        String[] words = s.trim().split(" ");
        int len = words.length;
        StringBuilder ans = new StringBuilder();
        ans.append(words[len - 1]);
        for (int i = len - 2; i >= 0; i --) {
            if (words[i].equals(""))
                continue;
            ans.append(" " + words[i]);
        }
        return ans.toString();
    }



    public static void main(String[] args) {
        String s1 = "the sky is blue";
        String s2 = "hah   is hah's farther!  ";
        String s = reverseWords3(s1);
        System.out.println(s);
    }
}
