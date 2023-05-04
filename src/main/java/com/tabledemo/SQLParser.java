package com.tabledemo;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;

import lombok.Data;

public class SQLParser {
    private static void parser() throws Exception {
        String fileName = "/Users/xiaomanwang/user_sql.csv";
        List<String> lines = FileUtils.readLines(new File(fileName), Charset.defaultCharset());
        Map<String,Integer> wordCountMap = new HashMap<>();
        for(String line : lines) {
            if(StringUtils.isNotBlank(line)) {
                List<String> words = getWords(line.toLowerCase());
                words.forEach(word -> {
                    int count = wordCountMap.getOrDefault(word, 0) + 1;
                    wordCountMap.put(word, count);
                });
            }
        }
        
        // count
        System.out.println("wordCount size:" + wordCountMap.size());
        
        // for each
        List<WordCount> wcs = new ArrayList<>();
        for(Entry<String, Integer> wct : wordCountMap.entrySet()) {
            if(StringUtils.isEmpty(wct.getKey()) || Objects.equal(wct.getKey(), "'")) {
                continue;
            }
            
            if(wct.getKey().contains(".")) {
                continue;
            }
            
            if(wct.getValue() <= 2) {
                continue;
            }
            
            String word = wct.getKey();
            try {
                // 如果能够转换为int数值，那么跳过
                Integer.valueOf(word);
                continue;
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            WordCount wc = new WordCount();
            wc.setWord(word);
            wc.setCount(wct.getValue());
            wcs.add(wc);
        }
        
        // statistic
        Collections.sort(wcs, new Comparator<WordCount>() {
            @Override
            public int compare(WordCount o1, WordCount o2) {
                return o2.getCount() - o1.getCount();
            }});
        
        System.out.println("word count:"+wcs.size());
        
        System.out.println("---------");
        wcs.subList(0, 1000).forEach(wc -> System.out.println(wc.getWord()+":"+wc.getCount()));;
    }
    
    private static List<String> getWords(String str){
        return Splitter.on(CharMatcher.anyOf(" ,();")).splitToList(str);
    }
    
    @Data
    private static class WordCount{
        private String word;
        private int count;
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            WordCount other = (WordCount) obj;
            if (word == null) {
                if (other.word != null)
                    return false;
            } else if (!word.equals(other.word))
                return false;
            return true;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((word == null) ? 0 : word.hashCode());
            return result;
        }
    }
    
    public static void main(String[] args) {
        String str = "select source,count(*) from base.adme_base__20220607_v1_1_0";
        System.out.println(getWords(str));
        
        try {
            parser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
