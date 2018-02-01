/**
 * 
 */
package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linkedme
 *
 */
public class EncodeAndDecodeTinyURL {
    private static List<String> urls = new ArrayList<>();

    // Encodes a URL to a shortened URL.
    public static String encode(String longUrl) {
        urls.add(longUrl);
        return "http://tinyurl.com/"+(urls.size()-1);
    }

    // Decodes a shortened URL to its original URL.
    public static String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl.substring(shortUrl.lastIndexOf("/")+1));
        return urls.get(index);
    }

    public static void main(String[] args) {
        System.out.println(encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println(decode("http://tinyurl.com/0"));
    }
}
