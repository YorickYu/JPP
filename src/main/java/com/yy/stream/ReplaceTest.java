package com.yy.stream;

import com.yy.stream.model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ReplaceTest {
    public static void main(String[] args) {
        List<Content> contents = new ArrayList<>();
        contents.add(new Content("", "https://", "aaaaaa", 100, 123));
        contents.add(new Content("", "https://", "bbbbbb", 0, 123));
        contents.add(new Content("", "https://", "cccccc", 300, 123));
        contents.add(new Content("", "https://", "dddddd", 400, 123));

        int size = contents.size();
        IntStream.range(0, size).forEach(idx -> {
            StringBuilder builder = new StringBuilder("步骤");
            Content content = contents.get(idx);
            builder.append(idx).append("/").append(size);
            content.setName(builder.toString());
//            content.setWidth(content.getWidth() == 0?0:content.getWidth());
//            content.setHeight(content.getHeight() == 0?0:content.getHeight());
        });


        String url = "/pages/recipesPackage/recipesDetail/recipesDetail?recipe_id=1233&product_id=3211";
        String[] split = url.split("\\?");

        System.out.printf("123");



    }
}
