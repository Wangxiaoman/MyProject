package nio;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class SelectorExample {
    public static void main(String[] args) {

    }

    public static void createSelector(SelectableChannel channel) {
        Selector selector = null;
        try {
            // 创建SelectionKey
            selector = Selector.open();
            channel.configureBlocking(false);
            SelectionKey sk =
                    channel.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT);

            // 处理SelectionKey
            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        System.out.println("accept");
                    } else if (key.isConnectable()) {
                        System.out.println("connect");
                    } else if (key.isReadable()) {
                        System.out.println("read");
                    } else if (key.isWritable()) {
                        System.out.println("write");
                    }
                    iterator.remove();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
