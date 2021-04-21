package com.yy.string;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoinerTest {

    public static void main(String[] args) {
        List<String> users = Arrays.asList("George", "Sally", "Fred");
        String commaSeparatedUsers = users.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(commaSeparatedUsers);
    }
}
