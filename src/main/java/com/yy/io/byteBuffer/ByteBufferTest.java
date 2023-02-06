package com.yy.io.byteBuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferTest {

    /**
     * Invariants: mark <= position <= limit <= capacity
     * private int mark = -1;
     * private int position = 0;
     * private int limit;
     * private int capacity;
     *
     * byteBuffer.flip(); // 切换成读模式
     * byteBuffer.clear(); // 切换成写模式，不保留未读数据
     * byteBuffer.compact(); // 切换成写模式，保留未读数据
     *
     * byte b = byteBuffer.get(); // 读数据
     * byte b1 = byteBuffer.get(1); // 读数据，不改变 position
     *
     * byteBuffer.rewind(); // position 回到 0
     *
     * byteBuffer.mark(); // mark 当前 position
     * byteBuffer.reset(); // position 回到 mark 的位置
     *
     * @param args
     */
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
