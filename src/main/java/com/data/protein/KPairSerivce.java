package com.data.protein;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Splitter;
import com.google.common.io.Files;

public class KPairSerivce {

    // 计数
    public static Map<String, Integer> counter(List<String> lines, String splitChar) {
        Map<String, Integer> kcMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(lines)) {
            for (String line : lines) {
                List<String> keyList = Splitter.on(splitChar).trimResults().omitEmptyStrings().splitToList(line);
                for(String key : keyList) {
                    int count = kcMap.getOrDefault(key, 0);
                    kcMap.put(key, count + 1);
                }
            }
        }
        return kcMap;
    }

    // 将一行，拆分，并排序放入list中
    private static List<String> kSplitSort(String keysLine, String splitChar) {
        if (StringUtils.isBlank(keysLine)) {
            return Collections.emptyList();
        }

        List<String> keys = Splitter.on(splitChar).trimResults().omitEmptyStrings().splitToList(keysLine);
        Set<String> keySet = new TreeSet<>(keys);
        return new ArrayList<>(keySet);
    }

    // 将一行数据对应的一组关键词，两两组合形成KPair
    public static List<KPair> getKPairByLine(List<String> klist) {
        if (!CollectionUtils.isEmpty(klist)) {
            List<KPair> result = new ArrayList<>();
            for (int i = 0; i < klist.size(); i++) {
                String key1 = klist.get(i);
                for (int j = i + 1; j < klist.size(); j++) {
                    KPair kp = new KPair();
                    kp.setK1(key1);
                    String key2 = klist.get(j);
                    kp.setK2(key2);
                    result.add(kp);
                }
            }
            return result;
        }
        return Collections.emptyList();
    }
    
    // 获取所有成对的key组合
    public static List<KPair> getKPair(List<String> lines, String splitChar){
        if(CollectionUtils.isEmpty(lines)) {
            return Collections.emptyList();
        }
        
        List<KPair> result = new ArrayList<>();
        for(String line : lines) {
            List<String> klist = kSplitSort(line, splitChar);
            List<KPair> kpList = getKPairByLine(klist);
            result.addAll(kpList);
        }
        return result;
    }
    
    public static Map<KPair,Integer> getKPairCounter(List<KPair> kpList){
        Map<KPair, Integer> result = new HashMap<>();
        if(!CollectionUtils.isEmpty(kpList)) {
            for(KPair kp : kpList) {
                int count = result.getOrDefault(kp, 0);
                result.put(kp, count + 1);
            }
        }
        return result;
    }
    
    public static List<KPair> getResult(List<String> keyLines, String splitChat){
        List<KPair> result = new ArrayList<>();
        
        // 获取keyCount
        Map<String,Integer> countMap = counter(keyLines, splitChat);
        
        // 获取所有key组合
        List<KPair> kpList = getKPair(keyLines, splitChat);
        
        // 获取每组的数量
        Map<KPair,Integer> kpMap = getKPairCounter(kpList);
        
        for(Entry<KPair, Integer> entry : kpMap.entrySet()) {
            KPair kp = entry.getKey();
            int count = entry.getValue();
            
            kp.setPCount(count);
            kp.setC1(countMap.get(kp.getK1()));
            kp.setC2(countMap.get(kp.getK2()));
            
            result.add(kp);
        }
        
        return result;
    }
    
    public static List<String> getFileLines() throws Exception{
        String fileName = "/Users/xiaomanwang/tools/protein/protein_cell";
        File file = new File(fileName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        return lines;
    }
    
    public static void main(String[] args) throws Exception {
        List<String> lines = getFileLines();
        List<KPair> kpList = getResult(lines, ",");
        System.out.println("kpList size : "+ kpList.size());
        
        List<KPair> result = kpList.stream().filter(kp -> kp.getPCount() > 1).collect(Collectors.toList());
        System.out.println("result size : " + result.size());
        
        for(KPair kp : result) {
            System.out.println(kp);
        }
    }
}
