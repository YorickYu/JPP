package com.yy.io.fileChannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannel {

    public static void main(String[] args) {
        try {

            FileChannel from = new FileInputStream("src/main/java/com/yy/io/fileChannel/data.txt").getChannel();
            FileChannel to = new FileOutputStream("src/main/java/com/yy/io/fileChannel/to.txt").getChannel();


            long res = from.transferTo(0, from.size(), to);
            System.out.println(res);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
