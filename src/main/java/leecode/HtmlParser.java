package leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaomanwang 
 *  (https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]
 *  (http|https):\/\/([\w.!@#$%^&*()_+-=])*\s*
 *  \s*(?i)href\s*=\s*["|\']+([^"\'>\s]*)
 */
public class HtmlParser {
    /*
     * @param content: content source code
     * @return: a list of links
     */
    public static List<String> parseUrls(String content) {
        // write your code here
        Pattern pattern = Pattern
                .compile("<a[^>]*href=(\\\"([^\\\"]*)\\\"|\\'([^\\']*)\\'|([^\\\\s>]*))[^>]*>(.*?)</a>");
        Matcher matcher = pattern.matcher(content);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            String url = matcher.group(0).intern();
            result.add(url);
        }
        return result;
    }
    
    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "*?=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
          String r = m.group(1);
          result.add(r);
        }
        return result;
      }

    public static void main(String[] args) {
        StringBuffer c = new StringBuffer();
        c.append(" <html>");
        c.append("    <body>");
        c.append("      <div>");
        c.append("        <a href=\"http://www.google.com\" class=\"text-lg\">Google</a>");
        c.append("      </div>");
        c.append("      <div>");
        c.append("        <a href=\"https://www.linkedin.com\">Linkedin</a>");
        c.append("        <a href = \"http://github.io\">LintCode</a>");
        c.append("        <a href =  \"http://www.lintcode.com\">LintCode</a>");
        c.append("      </div>");
        c.append("    </body>");
        c.append("  </html>");
        System.out.println(parseUrls(c.toString()));
        System.out.println(match(c.toString(),"a","href"));
    }
}
