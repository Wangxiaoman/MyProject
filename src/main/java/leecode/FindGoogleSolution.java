package leecode;

import java.util.List;

/**
 * @author xiaomanwang
 *
 * 给您一个字符串形式的C ++文件（每行是一个字符串），我们希望您在注释行中找到“ Google”。
 * 如果注释行中有“ Google”，则返回true，否则返回false。
 * C++有两种注释方式，一种是单行注释 //，代表着//后面的本行内容均为注释，
 * 另一种是多行注释，/* 和*\/ 这两者之间的部分均为注释。
 */
public class FindGoogleSolution {
    /**
     * @param s: The c++ file
     * @return: return if there is "Google" in commet line
     */ 
    public boolean findGoogle(List<String> s) {
        // Write your code here.
        boolean ns = false;
        for(String str : s) {
            if(str.contains("//")) {
                String temp = str.substring(str.indexOf("//")+1, str.length());
                if(temp.contains("Google")) {
                    return true;
                }
                continue;
            }
            if(str.contains("/*")) {
                ns = true;
                String temp = str.substring(str.indexOf("/*")+1, str.length());
                if(temp.contains("Google")) {
                    return true;
                }
                continue;
            }
            if(str.contains("*/")) {
                String temp = str.substring(0,str.indexOf("*/"));
                if(temp.contains("Google")) {
                    return true;
                }
                ns = false;
                continue;
            }
            if(ns) {
                if(str.contains("Google")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("abc//abd".indexOf("//"));
    }
}
