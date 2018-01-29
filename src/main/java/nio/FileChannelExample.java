/**
 * 
 */
package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author linkedme
 *
 */
public class FileChannelExample {
    private static RandomAccessFile file;

    public static void main(String[] args) throws Exception {
        file = new RandomAccessFile("/Users/linkedme/Documents/test.txt", "rw");
        FileChannel fc = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100); 
        
        int size = fc.read(buffer);
        
        while(size != -1){
            buffer.flip();
            while(buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            System.out.println();
            buffer.clear();
            size = fc.read(buffer);
        }
        fc.close();
    }
}
