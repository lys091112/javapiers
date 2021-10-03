package com.xianyue.basictype.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuhongjun
 * @since 2020-01-17
 */
public class NioServerV2 {

    private Selector selector;
    private Selector rWSelector;
    private SelectionKey rwKey;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    private boolean stop = false;

    private String receiveMessage;

    public void start() throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        ssc.configureBlocking(false);
        // 进行服务的绑定
        ssc.bind(new InetSocketAddress("localhost", 8001));

        // 通过open()方法找到Selector
        selector = Selector.open();
        rWSelector = Selector.open();
        // 注册到selector，等待连接
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(
            () -> {
                try {
                    while (!stop) {
                        selector.select();
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = keys.iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (!key.isValid()) {
                                continue;
                            }
                            if (key.isAcceptable()) {
                                accept("select", key);
                            } else if (key.isReadable()) {
                                read(key);
                            } else if (key.isWritable()) {
                                write(key);
                            }
                            keyIterator.remove(); // 该事件已经处理，可以丢弃
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            })
            .start();

    }

    private void write(SelectionKey key) throws IOException, ClosedChannelException {
        SocketChannel channel = (SocketChannel) key.channel();
        System.out.println("write:" + receiveMessage);

        sendBuffer.clear();
        sendBuffer.put(receiveMessage.getBytes());
        sendBuffer.flip();
        channel.write(sendBuffer);
        channel.register(selector, SelectionKey.OP_READ);
    }

    private void write(String name, SelectionKey key) throws IOException, ClosedChannelException {
        SocketChannel channel = (SocketChannel) key.channel();
        System.out.println("name=" + name + " --> write:" + receiveMessage);

        sendBuffer.clear();
        sendBuffer.put(receiveMessage.getBytes());
        sendBuffer.flip();
        channel.write(sendBuffer);
        rwKey.interestOps(SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // Clear out our read buffer so it's ready for new data
        this.readBuffer.clear();
//        readBuffer.flip();
        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            key.cancel();
            socketChannel.close();

            return;
        }

        receiveMessage = new String(readBuffer.array(), 0, numRead);
        System.out.println("read:" + receiveMessage);

        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void read(String name, SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // Clear out our read buffer so it's ready for new data
        this.readBuffer.clear();
//        readBuffer.flip();
        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            key.cancel();
            socketChannel.close();

            return;
        }

        receiveMessage = new String(readBuffer.array(), 0, numRead);
        System.out.println("name=" + name + " --> read:" + receiveMessage);
        rwKey = socketChannel.register(rWSelector, 0);
        rwKey.interestOps(SelectionKey.OP_WRITE);
    }

    // TODO select()方法要在register 之后，是因为 start的时候已经开启了rWselector.select().这里调用register()在底层
    // 实现中会和 select()竞争同一把锁，造成线程被阻塞到这一步
    private void accept(String name, SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        rwKey = clientChannel.register(rWSelector, 0);
        rwKey.interestOps(SelectionKey.OP_READ);
        rWSelector.wakeup();
        startRW();
        System.out.println("name=" + name + " --> a new client connected " + clientChannel.getRemoteAddress());
    }

    private void startRW() {
        new Thread(
            () -> {
                while (!stop) {
                    try {
                        System.out.println("------------------");
                        rWSelector.select();
                        Set<SelectionKey> keys = rWSelector.selectedKeys();
                        System.out.println("rwKeySize=" + keys.size());
                        Iterator<SelectionKey> keyIterator = keys.iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (!key.isValid()) {
                                continue;
                            }
                            if (key.isReadable()) {
                                read("rWSelector", key);
                            } else if (key.isWritable()) {
                                write("rWSelector", key);
                            }
                            keyIterator.remove(); // 该事件已经处理，可以丢弃
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("server started...");
        new NioServerV2().start();
    }
}
