package com.yy.leecode.slidingWindow;

//给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
//
//
//
// 示例 1：
//
// 输入：
//  s = "barfoothefoobarman",
//  words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
//
//
// 示例 2：
//
// 输入：
//  s = "wordgoodgoodgoodbestword",
//  words = ["word","good","best","word"]
//输出：[]
//
// Related Topics 哈希表 双指针 字符串

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindSubwords {


    /*
    测试用例:s = "a" words = ["a","a"]
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> list = new ArrayList<>(); // return list

        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return list;
        HashMap<String, Integer> map = new HashMap<>();

        int l = words[0].length(); // word 等长
        int pin = l * words.length;

        if (s.length() < pin)
            return list;

        int loop = 0;
        do {
            String window = s.substring(loop, pin+loop);
            map.clear();
            for (String w:words) { // words map
                map.put(w, map.getOrDefault(w, 0)+1);
            }

            for (int i = 0; i < window.length(); i += l) {

                String fakeWord = window.substring(i, i + l);
                Integer count = map.getOrDefault(fakeWord, 0);
                if (count > 1) {
                    map.put(fakeWord, --count);
                }else if (count == 1) {
                    map.remove(fakeWord);
                }
            }
            if (map.size() == 0)
                list.add(loop);
            loop += 1;
        }while (loop < s.length() - pin + 1);

        return list;
    }


    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "a";
        String[] words = {"a","a"};
        List<Integer> substring = findSubstring(s, words);
        System.out.println("substring = " + substring);
    }
}
