package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GFSClient extends BaseGFSClient {
    private int chunkSize;
    
    /*
    * @param chunkSize: An integer
    */public GFSClient(int chunkSize) {
        // do intialization if necessary
        this.chunkSize = chunkSize;
    }

    /*
     * @param filename: a file name
     * @return: conetent of the file given from GFS
     */
    public String read(String filename) {
        // write your code here
        StringBuffer sb = new StringBuffer();
        String c0 = readChunk(filename, 0);
        if(c0 == null || c0 == "") {
            return null;
        } else {
            sb.append(c0);
        }
        for (int i = 1;; i++) {
            String content = readChunk(filename, i);
            if (content != null && content != "") {
                sb.append(content);
            } else {
                return sb.toString();
            }
        }
    }

    /*
     * @param filename: a file name
     * @param content: a string
     * @return: nothing
     */
    public void write(String filename, String content) {
        clearFile(filename);
        // write your code here
        if(content == null) {
            return;
        }
        
        List<String> contentList = splitContent(content);
        for (int i = 0; i < contentList.size(); i++) {
            writeChunk(filename, i, contentList.get(i));
        }
    }
    
    private List<String> splitContent(String content) {
        List<String> result = new ArrayList<>();
        int ss = content.length() / chunkSize;
        int size = (content.length() % chunkSize == 0) ? ss : ss + 1;
        
        for (int i = 0; i < size; i++) {
            String sc =
                    content.substring(i * chunkSize, Math.min((i + 1) * chunkSize, content.length()));
            result.add(sc);
        }
        return result;
    }
    
    private void clearFile(String fileName) {
        for(int i=0;;i++) {
            String c = readChunk(fileName, i);
            if(c != null) {
                writeChunk(fileName, i, null);
            } else {
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        GFSClient client = new GFSClient(5);
        System.out.println(client.read("a.txt"));
        client.write("a.txt", "World");
        System.out.println(client.read("a.txt"));
        client.write("b.txt", "111112222233");
        System.out.println(client.read("b.txt"));
        client.write("b.txt", "aaaaabbbbb");
        System.out.println(client.read("b.txt"));
    }
    
}

class BaseGFSClient {
    private Map<String,List<String>> chunk_list = new HashMap<>();
    public BaseGFSClient() {}
    public String readChunk(String filename, int chunkIndex) {
        List<String> list = chunk_list.get(filename);
        if(list== null || list.isEmpty()) {
            return null;
        } else if(list.size() <= chunkIndex) {
            return null;
        }
        return list.get(chunkIndex);
    }
    public void writeChunk(String filename, int chunkIndex,
                           String content) {
        List<String> list = chunk_list.getOrDefault(filename, new ArrayList<>());
        list.add(content);
        chunk_list.put(filename, list);
        System.out.println("chunk_list:"+chunk_list);
    }
}
