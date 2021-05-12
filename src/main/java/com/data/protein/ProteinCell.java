package com.data.protein;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

public class ProteinCell {
    public static void keywords() throws Exception {
        String fileName = "/Users/xiaomanwang/tools/protein/protein_cell";
        String fName = "/Users/xiaomanwang/tools/protein/protein_cell_result";
        File file = new File(fileName);
        File newFile = new File(fName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        List<Keywords> result = new ArrayList<>();
        
        for(String l : lines) {
            result.addAll(getPairKeywords(l));
        }
        
        List<String> fileList = new ArrayList<>();
        for(Keywords k : result) {
            fileList.add(k.toString());
        }
        
//        Map<String,Integer> map = getKeyMap(lines);
//        for(Keywords kw : result) {
//            int count1 = 0;
//            int count2 = 0;
//            try {
//                count1 = map.get(kw.getK1());
//                count2 = map.get(kw.getK2());
//            }catch(Exception x) {
//                System.out.println(kw);
//            }
//            kw.setCount1(count1);
//            kw.setCount2(count2);
//        }
        
//        Map<Keywords,Integer> kMap = new HashMap<>();
//        for(Keywords k : result) {
//            int count = kMap.getOrDefault(k, 0);
//            k.setPairCount(count + 1);
//            kMap.put(k, count + 1);
//            if(count > 1) {
//                System.out.println(k);
//            }
//        }
        CharSink sink = Files.asCharSink(newFile, Charsets.UTF_8);
        sink.writeLines(fileList);
    }
    
    public static Map<String,Integer> getKeyMap(List<String> lines){
        Map<String,Integer> result = new HashMap<>();
        for(String l: lines) {
            List<String> list = Splitter.on(",").splitToList(l);
            for(String k : list) {
                if(StringUtils.isNotBlank(k)) {
                    String key = k.trim();
                    int count = result.getOrDefault(key, 0);
                    result.put(key, count + 1);
                }
            }
        }
        return result;
    }
    
    public static List<Keywords> getPairKeywords(String keywords){
        List<String> list = Splitter.on(",").splitToList(keywords);
        List<String> klist = new ArrayList<>();
        for(String k : list) {
            if(StringUtils.isNotBlank(k)) {
                klist.add(k.trim());
            }
        }
        
        Collections.sort(klist);
        List<Keywords> result = new ArrayList<Keywords>();
        for(int i=0;i<klist.size();i++) {
            String key1 = klist.get(i);
            for(int j=i+1;j<klist.size();j++) {
                Keywords kw = new Keywords();
                kw.setK1(key1);
                kw.setK2(klist.get(j));
                result.add(kw);
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        keywords();
    }
}
