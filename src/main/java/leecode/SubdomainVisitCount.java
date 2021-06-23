package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// 811. 子域名访问计数
public class SubdomainVisitCount {
    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> domainMap = new HashMap<>();
        for(String cpdomain : cpdomains) {
            String[] list = cpdomain.split(" ");
            int count = Integer.valueOf(list[0]);
            String[] domainList = list[1].split("\\.");
            String url = "";
            for (int i = domainList.length - 1; i >= 0; i--) {
                url = (i == domainList.length - 1) ? domainList[i] : domainList[i] + "." + url;
                int currentCount = domainMap.getOrDefault(url, 0);
                domainMap.put(url, currentCount + count);
            }
        }
        List<String> result = new ArrayList<>();
        for(Entry<String, Integer> entry : domainMap.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }
    
    public static void main(String[] args) {
        String[] input1 = new String[] {"9001 discuss.leetcode.com"};
        String[] input2 = new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subdomainVisits(input1));
        System.out.println(subdomainVisits(input2));
        
        System.out.println("discuss.leetcode.com".split("\\.")[0]);
    }
}
