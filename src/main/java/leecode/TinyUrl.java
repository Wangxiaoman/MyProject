package leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiaomanwang 对算法的设计不做要求，评测标准如下： 短网址的key的长度应该等于6 （不算域名和反斜杠）。 可以使用的字符只有 [a-zA-Z0-9]. 比如:
 *         abcD9E 任意两个长的url不会对应成同一个短url，反之亦然。
 */
public class TinyUrl {
    Map<String,String> shortUrlMap = new HashMap<>();
    Map<String,String> urlMap = new HashMap<>();
    
    private String urlPrefix = "http://tiny.url/";
    
    /*
     * @param url: a long url
     * @return: a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        // write your code here
        String surl = urlMap.get(url);
        if(surl == null) {
            surl = generateShortUuid();
            while(shortUrlMap.containsKey(surl)) {
                surl = generateShortUuid();
            }
            shortUrlMap.put(surl, url);
            urlMap.put(url, surl);
        }
        return urlPrefix + surl;
    }

    /*
     * @param url: a short url starts with http://tiny.url/
     * @return: a long url
     */
    public String shortToLong(String url) {
        // write your code here
        String ucode = url.replace(urlPrefix, "");
        return shortUrlMap.get(ucode);
    }

    public static String[] chars =
            new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                    "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4",
                    "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                    "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    public static String generateTicket() {
        String ticket = UUID.randomUUID().toString();
        return ticket.replaceAll("-", "");
    }

    public String generateShortUuid() {
        // 调用Java提供的生成随机字符串的对象：32位，十六进制，中间包含-
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");

        for (int i = 2; i < 8; i++) { // 共8组，取后6组
            String str = uuid.substring(i * 4, i * 4 + 4); // 每组4位
            int x = Integer.parseInt(str, 16); // 将4位str转化为int 16进制下的表示

            // 用该16进制数取模62（十六进制表示为314（14即E）），结果作为索引取出字符
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
    
    public static void main(String[] args) {
        TinyUrl tu = new TinyUrl();
        String surl = tu.longToShort("http://www.lintcode.com/faq/?id=10");
        System.out.println(surl);
        System.out.println(tu.shortToLong(surl));
    }
}
