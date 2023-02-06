package com.yy.emoji;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @className: EmojiTest
 * @description:
 * @author: yloopdaed
 * @date: 2022/6/26
 **/
public class EmojiTest {

    public static void main(String[] args) {
        // U+1F471 U+200D U+2640 U+FE0F
        // String s = "\uD83D\uDD75️\u200D♀️";
        String s = "🏃‍♀️";
        int le = s.length();
        IntStream stream = s.codePoints();
        AtomicInteger ai = new AtomicInteger();
        stream.forEach(item -> {
            int charCount = Character.charCount(item);
            ai.incrementAndGet();
            System.out.println(charCount);
        });
        int count2 = s.codePointCount(0, le);
        System.out.println(s);
    }
}
