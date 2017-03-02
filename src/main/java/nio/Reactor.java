package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    /*
     * Alternatively, use explicit SPI provider: SelectorProvider p = SelectorProvider.provider();
     * selector = p.openSelector(); serverSocket = p.openServerSocketChannel();
     */
    public void run() { // normally in a new Thread
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()){
                    dispatch((SelectionKey) (it.next()));
                }
                selected.clear();
            }
        } catch (IOException ex) {
            /* ... */ }
    }

    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null)
            r.run();
    }

    class Acceptor implements Runnable { // inner
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null)
                    new Handler(selector, c);
            } catch (IOException ex) {
                /* ... */ }
        }
    }

    final class Handler implements Runnable {
        final SocketChannel socket;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(100);
        ByteBuffer output = ByteBuffer.allocate(100);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        Handler(Selector sel, SocketChannel c) throws IOException {
            socket = c;
            c.configureBlocking(false);
            // Optionally try first read now
            sk = socket.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        boolean inputIsComplete() {
            return true;
            /* ... */ }

        boolean outputIsComplete() {
            return true;
            /* ... */ }

        void process() { /* ... */ }

        public void run() {
            try {
                if (state == READING)
                    read();
                else if (state == SENDING)
                    send();
            } catch (IOException ex) {
                /* ... */ }
        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                // Normally also do first write now
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        void send() throws IOException {
            socket.write(output);
            if (outputIsComplete())
                sk.cancel();
        }
    }
}


