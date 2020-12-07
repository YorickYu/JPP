package com.yy.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class NIOTest {

    public static void main(String[] args) throws IOException {

        LinkedList<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // 服务端建立连接
        serverSocketChannel.bind(new InetSocketAddress(8000));
        serverSocketChannel.configureBlocking(false); // 非阻塞

        while (true) {
            SocketChannel clientSocketChannel = serverSocketChannel.accept(); // socket客户端连接

            if (clientSocketChannel != null) {
                clientSocketChannel.configureBlocking(false);
                clients.add(clientSocketChannel);
                System.out.println(clientSocketChannel.socket().getPort());
            }

            // 遍历已连接的客户端socket
            for (SocketChannel client: clients) { // 这里涉及到用户态和内核态的切换
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println(new String(bytes));
                }
            }
        }


    }


}
