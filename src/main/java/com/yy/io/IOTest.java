package com.yy.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOTest {


    public static void main(String[] args) throws Exception {

        // 服务端建立连接
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            try {
                // 1 获取新的连接, 阻塞
                Socket socket = serverSocket.accept();
                // 2 每一个新的连接都创建一个线程，负责读取数据
                new Thread(() -> {
                    try {
                        int len;
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();
                        // 3 按字节流方式读取数据
                        while ((len = inputStream.read(data)) != -1) {
                            System.out.println(new String(data, 0, len));
                        }
                    } catch (IOException e) {
                    }
                }).start();

            } catch (IOException e) {
            }
        }
    }


}
