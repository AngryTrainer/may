package selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server implements Runnable {
	 
    public static final int MAX_BUFFER_SIZE = 1024;
 
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ServerSocket serverSocket;
    private InetSocketAddress inetSocketAddress;
    private Thread thread;
    private int port;
    private ByteBuffer byteBuffer;
 
    public Server(int port) {
        this.port = port;
        byteBuffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);
    }
 
    @Override
    public void run() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            inetSocketAddress = new InetSocketAddress(port);
            serverSocket = serverSocketChannel.socket();
            serverSocket.bind(inetSocketAddress);
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException ex) {
            System.out.println("Excep 1");
        }
 
        while (true) {
 
            int count = 0;
            try {
                count = selector.select();
            } catch (IOException ex) {
                System.out.println("Excep 2");
            }
 
            if (count == 0) {
                continue;
            }
 
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
 
            while (iterator.hasNext()) {
 
                SelectionKey key = iterator.next();
                Socket socket = null;
                SocketChannel channel = null;
 
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                	System.out.println("Accepted Connection...");
                    try {
                        socket = serverSocket.accept();
                        channel = socket.getChannel();
                    } catch (IOException e) {
                        System.out.println("Unable to accept channel");
                        key.cancel();
                    }
                    if (channel != null) {
                        try {
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                        } catch (IOException ex) {
                            System.out.println("Excep 3");
                        }
                    }
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                  //  System.out.println("Ready for reading");
                	channel = (SocketChannel) key.channel();
                    try {
                        channel.read(byteBuffer);
                    } catch (IOException ex) {
                        System.out.println("Disconnected...");
                        System.out.println("Server shutdown...");
                        System.exit(0);
                        
                    }
                    byteBuffer.flip();
                   // byte tmp[] = new byte[MAX_BUFFER_SIZE];
                    for (int i = 0; i < byteBuffer.limit(); i++) {
                        System.out.print((char) byteBuffer.get(i));
                    }
                   
//                    System.out.println("22");
                }
            }
            keys.clear();
        }
 
    }
 
    public void runServer() {
        thread = new Thread(this);
        thread.start();
    }
 
    private void byteToString() {
 
    }
 
    public void join() throws InterruptedException {
        thread.join();
    }
}