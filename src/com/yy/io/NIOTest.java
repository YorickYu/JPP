package com.yy.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOTest {


        /**
         * serverSelector负责轮询是否有新的连接,clientSelector负责轮询连接是否有数据可读.
         * 服务端监测到新的连接不再创建一个新的线程,而是直接将新连接绑定到clientSelector上,这样不用IO模型中1w个while循环在死等
         * clientSelector被一个while死循环包裹,如果在某一时刻有多条连接有数据可读通过 clientSelector.select(1)方法轮询出来进而批量处理
         * 数据的读写以内存块为单位
         *
         * @param args
         * @throws IOException
         */
        public static void main(String[] args) throws IOException {
            Selector serverSelector = Selector.open();
            Selector clientSelector = Selector.open();

            new Thread(() -> {
                try {
                    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                    serverSocketChannel.socket().bind(new InetSocketAddress(8000));
                    serverSocketChannel.configureBlocking(false); // 配置阻塞
                    serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                    while (true) {
                        // 轮询监测是否有新的连接
                        if (serverSelector.select(1) > 0) {
                            Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                            while (keyIterator.hasNext()) {
                                SelectionKey selectionKey = keyIterator.next();
                                if (selectionKey.isAcceptable()) {
                                    try {
                                        //(1)每来一个新连接不需要创建一个线程而是直接注册到clientSelector
                                        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                                        socketChannel.configureBlocking(false);
                                        socketChannel.register(clientSelector, SelectionKey.OP_READ);
                                    } finally {
                                        keyIterator.remove();
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    while (true) {
                        // (2)批量轮询是否有哪些连接有数据可读
                        if (clientSelector.select(1) > 0) {
                            Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                            while (keyIterator.hasNext()) {
                                SelectionKey selectionKey = keyIterator.next();
                                if (selectionKey.isReadable()) {
                                    try {
                                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                        //(3)读取数据以块为单位批量读取
                                        socketChannel.read(byteBuffer);
                                        byteBuffer.flip();
                                        System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
                                                .toString());
                                    } finally {
                                        keyIterator.remove();
                                        selectionKey.interestOps(SelectionKey.OP_READ);
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

}
