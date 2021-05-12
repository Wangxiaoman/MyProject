package com.data.protein;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

public class Topics {

    public static Map<String, Topic> initTopic() throws Exception {
        String fileName = "/Users/xiaomanwang/tools/protein/topic";
        File file = new File(fileName);
        Map<String, Topic> result = new HashMap<>();
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        for (String l : lines) {
            Topic t = stringConvertTopic(l);
            result.put(t.getName(), t);
        }
        System.out.println("init finish, size:" + result.size());
        return result;
    }

    public static Topic stringConvertTopic(String strTopic) {
        List<String> list = Splitter.on("#").splitToList(strTopic);
        Topic t = new Topic();
        t.setName(list.get(0));
        t.setLevel(Integer.valueOf(list.get(1)));
        JSONArray ja = null;
        try {
            ja = JSON.parseArray(list.get(2));
        } catch (Exception ex) {
            System.out.println(strTopic);
        }
        if (ja != null && !ja.isEmpty()) {
            List<String> parent = new ArrayList<>();
            for (int i = 0; i < ja.size(); i++) {
                parent.add(ja.getJSONObject(i).getString("name"));
            }
            t.setParentName(parent);
        }
        return t;
    }

    public static void convertLevel3() throws Exception {
        String fileName = "/Users/xiaomanwang/tools/protein/topics.txt";
        String fName = "/Users/xiaomanwang/tools/protein/topics_pair";
        Map<String, Topic> topicMap = initTopic();
        List<String> fileList = new ArrayList<>();

        File file = new File(fileName);
        File newFile = new File(fName);

        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        for (String l : lines) {
            l = l.replace("\"", "");
            List<String> ts = Splitter.on(",").splitToList(l);
            Set<String> level3Set = new HashSet<>();
            for (String t : ts) {
                String tk = t.trim();
                level3Set.addAll(getLevel3Topic(tk, topicMap, new ArrayList<>()));
            }

            List<String> level3List = new ArrayList<>(level3Set);
            Collections.sort(level3List);
            
            for (int i = 0; i < level3List.size(); i++) {
                String key1 = level3List.get(i);
                for (int j = i + 1; j < level3List.size(); j++) {
                    fileList.add("'" + key1 + "','" + level3List.get(j) + "'");
                }
            }
        }

        CharSink sink = Files.asCharSink(newFile, Charsets.UTF_8);
        sink.writeLines(fileList);
    }

    public static List<String> getLevel3Topic(String name, Map<String, Topic> topicMap,
            List<String> level3) {
        Topic t = topicMap.get(name);
        if (t == null) {
            return level3;
        }
        if (Objects.equal(t.getLevel(), 3)) {
            level3.add(t.getName());
            return level3;
        }
        if (t.getLevel() < 3) {
            return level3;
        }
        if (t.getLevel() > 3) {
            List<String> parent = t.getParentName();
            for (String n : parent) {
                getLevel3Topic(n, topicMap, level3);
            }
            return level3;
        }
        return level3;
    }

    public static void main(String[] args) throws Exception {
        // Map<String,Topic> topicMap = initTopic();
        // String[] ss = new String[] {"Stem cell", "Lentivirus", "Human genetics", "Hematopoietic
        // stem cell transplantation", "Genetic enhancement", "Developmental biology", "Color Vision
        // Defects", "Cell biology", "Cancer research", "Biology", "Adrenoleukodystrophy"};
        // for(String s : ss) {
        // System.out.println(getLevel3Topic(s, topicMap, new ArrayList<>()));
        // }

        convertLevel3();
    }

}
