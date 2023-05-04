package leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StandardBloomFilter {
    private int[] bloomHashList = null;
    private List<Function> functions = new ArrayList<>();

    public StandardBloomFilter(int k) {
        int bloomSize = 100000;
        bloomHashList = new int[bloomSize];
        for (int i = 0; i < k; i++) {
            Random r = new Random();
            int seed = r.nextInt(10000)+10000;
            functions.add(new Function(seed, bloomSize));
        }
    }

    public void add(String word) {
        for (Function f : functions) {
            int hash = f.hash(word);
            bloomHashList[hash] += 1;
        }
        System.out.println("add "+word+" bloomHashList:"+bloomHashList);
    }
    
    public void remove(String word) {
        for (Function f : functions) {
            int hash = f.hash(word);
            if(bloomHashList[hash] == 0) {
                return;
            }
            bloomHashList[hash] -= 1;
        }
        System.out.println("remove "+word+" bloomHashList:"+bloomHashList);
    }

    public boolean contains(String word) {
        boolean b = containsNoShow(word);
        System.out.println("contains: "+b+","+word+" bloomHashList:"+bloomHashList);
        return b;
    }
    
    public boolean containsNoShow(String word) {
        for (Function f : functions) {
            int hash = f.hash(word);
            if (bloomHashList[hash] == 0) {
                return false;
            }
        }
        return true;
    }

    static class Function {
        private int seed;
        private int size;

        public Function(int seed, int size) {
            this.seed = seed;
            this.size = size;
        }

        public int hash(String s) {
            int hashValue = 1;
            for (int i = 0; i < s.length(); i++) {
                hashValue = hashValue * seed * (s.charAt(i) - '0');
                hashValue = hashValue % size;
            }
            return Math.abs(hashValue);
        }
    }
    
    public static void main(String[] args) {
//        StandardBloomFilter sbf = new StandardBloomFilter(20);
//        sbf.add("world");
//        sbf.add("world1");
//        sbf.add("haha");
//        sbf.add("gaga");
//        sbf.add("one");
//        sbf.add("hello");
        
        StandardBloomFilter f = new StandardBloomFilter(4); 
        f.contains("kc");
        f.contains("dg");
        f.contains("hk");
        f.contains("efd");
        f.contains("ghi");
        f.add("cda");
        f.add("ej");
        f.add("fd");
        f.remove("fd");
        f.contains("fd");
        f.contains("hg");
        f.contains("bf");
        f.contains("hfb");
        f.contains("jae");
        f.contains("jd");
        f.contains("hj");
        f.add("bc");
        f.add("efi");
        f.add("cj");
        f.remove("cj");
        f.contains("cj");
        f.contains("fj");
        f.contains("ad");
        f.contains("gfa");
        f.contains("idf");
        f.contains("bfi");
        f.contains("jih");
        f.add("ga");
        f.add("fae");
        f.add("ck");
        f.remove("ck");
        f.contains("ck");
        f.contains("he");
        f.contains("kji");
        f.contains("fbh");
        f.contains("jk");
        f.contains("dic");
        f.contains("ag");
        f.add("ca");
        f.add("ki");
        f.add("efj");
        f.remove("efj");
        f.contains("efj");
        f.contains("ahg");
        f.contains("ik");
        
//        System.out.println(sbf.contains("world"));
//        System.out.println(sbf.contains("worl"));
        
//        for(int i=0;i<100;i++) {
//            Random r = new Random();
//            System.out.println(r.nextInt(1000));
//        }
    }
}
