package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferExample {

    public static void main(String[] args) {
        String filePath = "/Users/xiaoman/Documents/nio.txt";
        String fileToPath = "/Users/xiaoman/Documents/toNio.txt";
//        ReadFile(filePath);
//        ReadScatterFile(filePath);
        transferFile(filePath, fileToPath);
    }


    public static void ReadFile(String filePath) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            FileChannel fc = raf.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(2048);
            int readLine = fc.read(bb);

            while (readLine != -1) {
                // convert read mode
                bb.flip();
                while (bb.hasRemaining()) {
                    System.out.println(bb.get());
                }
                
                // clear buffer
                bb.clear();
                readLine = fc.read(bb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void ReadScatterFile(String filePath) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            FileChannel fc = raf.getChannel();
            ByteBuffer bb1 = ByteBuffer.allocate(4);
            ByteBuffer bb2 = ByteBuffer.allocate(2048);
            ByteBuffer[] bbs = {bb1,bb2};
            fc.read(bbs);

            System.out.println(bb1.getChar(0));
            System.out.println(bb2.getChar(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void transferFile(String sourcePath,String toPath){
        RandomAccessFile source = null;
        RandomAccessFile to = null;
        try {
            source = new RandomAccessFile(sourcePath, "rw");
            FileChannel sourceChannel = source.getChannel();
            to = new RandomAccessFile(toPath, "rw");
            FileChannel toChannel = to.getChannel();
            
            sourceChannel.transferTo(0, sourceChannel.size(), toChannel);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                source.close();
                to.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
