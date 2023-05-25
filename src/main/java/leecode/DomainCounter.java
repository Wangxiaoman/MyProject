package leecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * lintcode 1006
 * 
 * @author xiaomanwang
 *
 */
public class DomainCounter {
    /**
     * @param cpdomains: a list cpdomains of count-paired domains
     * @return: a list of count-paired domains we will sort your return value in output
     */
    public static List<String> subdomainVisits(String[] cpdomains) {
        // Write your code here
        Map<String, DomainCount> counter = new HashMap<>();
        for (String d : cpdomains) {
            String[] dc = d.split(" ");
            int c = Integer.valueOf(dc[0]);
            String[] ds = dc[1].split("\\.");
            String tempStr = "";
            for (int i = ds.length-1; i >= 0; i--) {
                if(i == ds.length-1) {
                    tempStr = ds[i];
                } else {
                    tempStr = ds[i] + "." + tempStr;
                }
                DomainCount dcounter = counter.getOrDefault(tempStr, new DomainCount(tempStr));
                dcounter.count = dcounter.count + c;
                counter.put(tempStr, dcounter);
            }
        }
        return counter.values().stream().map(d -> d.count + " " + d.domain)
                .collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        String[] ss = new String[] {"9001 discuss.lintcode.com"};
        System.out.println(subdomainVisits(ss));
    }
}


class DomainCount {
    public String domain;
    public int count;

    public DomainCount(String domain) {
        this.domain = domain;
    }
}
