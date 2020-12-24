package com.yy.leecode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
//                start = map.get(s.charAt(end));
            }
            ans = Math.max(ans, end - start + 1);
            map.put(alpha, end + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abba";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

}
