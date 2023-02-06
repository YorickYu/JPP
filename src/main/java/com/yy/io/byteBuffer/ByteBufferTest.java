package com.yy.io.byteBuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferTest {

    public static void main(String[] args) {

        String str = "hello world!";

        // 字符串转byte buffer
        // 1
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(str.getBytes());

        // 2
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode(str);
        String newStr = StandardCharsets.UTF_8.decode(buffer1).toString();

        // 3
        ByteBuffer buffer2 = ByteBuffer.wrap(str.getBytes());


    }
}
