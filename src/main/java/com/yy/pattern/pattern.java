package com.yy.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: pattern
 * @description:
 * @author: yloopdaed
 * @date: 2022/1/4
 **/
public class pattern {
    public static void main(String[] args) {
        String content = "\n" +
                "    *(%#$*kskkd多看看3828\uD83D\uDE012*￥（*……%12\n" +
                "阿三发射点发\n" +
                "阿三发射点        阿斯蒂芬撒地方\n" +
                "\n";

        // ^[\u4e00-\u9fa5] 汉字
        // ^[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、](.*?)\\d$
        // \s{1,9}

        // \\s*|\t|\r|\n \s{1,2}

        String line = filterLine(content);
        String space = formatSpace(line);
        String symbolStart = formatSymbolStart(space);

        System.out.println(symbolStart);
    }

    // 过滤 回车、换行
    public static String filterLine(String content) {
        Pattern p = Pattern.compile("\r|\n");
        Matcher m = p.matcher(content);
        return m.replaceAll("");
    }

    // 过滤 （连续）空格
    public static String formatSpace(String content) {
        Pattern p = Pattern.compile("\\s{1,9}");
        Matcher m = p.matcher(content);
        return m.replaceAll("");
    }

    // 过滤 开头特殊符号
    public static String formatSymbolStart(String content) {
        Pattern p = Pattern.compile("^[`~!@#$%^&*()_\\-+=<>?:\"{}|,.\\/;'·~！@#￥%……&*（）+={}|《》？：“”【】、；‘'，。、]+");
        Matcher m = p.matcher(content);
        return m.replaceAll("");
    }
}
