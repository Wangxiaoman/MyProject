package com.data.logp;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

public class LogP {

    public static Map<String, String> getDrugSmilesMap() throws Exception {
        String fileName = "/Users/xiaomanwang/tools/logp/drug_smiles_kv.txt";
        File file = new File(fileName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());

        Map<String, String> result = new HashMap<>();
        for (String line : lines) {
            List<String> drugSmiles = Splitter.on(":").splitToList(line);
            result.put(drugSmiles.get(0).toLowerCase(), drugSmiles.get(1));
        }
        return result;
    }

    public static void smilesLogP() throws Exception {
        String fileName = "/Users/xiaomanwang/tools/logp/logP.txt";
        String fName = "/Users/xiaomanwang/tools/logp/Smiles_logP.sql";
        File file = new File(fileName);
        File newFile = new File(fName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        List<String> result = new ArrayList<>();

        Map<String, String> drugSmilesMap = getDrugSmilesMap();
        int i=0;
        for (String l : lines) {
            ++i;
            try {
                JSONObject json = JSON.parseObject(l);
                JSONArray ja = json.getJSONArray("layout");
                JSONArray logPJa = ja.getJSONArray(ja.size() - 1);
                String drugName = logPJa.getString(0).trim();
                if(!StringUtils.isBlank(drugName)) {
                    List<String> drugNameList = Splitter.on(",").splitToList(drugName);
                    drugName = drugNameList.get(0).trim();
                    System.out.println(drugName);
                }
                
                String logP = logPJa.getString(1).trim();
                try {
                    Double.valueOf(logP);
                }catch(Exception ex) {
                    System.out.println(i + ":");
                    System.out.println(l);
                }
                int page = json.getIntValue("page");
                int table = json.getIntValue("table");
                String smiles = drugSmilesMap.get(drugName.toLowerCase());

                SmilesLogP smilesLogP = new SmilesLogP();
                smilesLogP.setLogP(logP);
                smilesLogP.setPage(page);
                smilesLogP.setDrugName(drugName);
                smilesLogP.setTableIndex(table);
                if (StringUtils.isNotBlank(smiles)) {
                    smilesLogP.setSmiles(smiles);
                }

                result.add(smilesLogP.toString());
//                System.out.println(smilesLogP.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(l);
            }
        }
        CharSink sink = Files.asCharSink(newFile, Charsets.UTF_8);
        sink.writeLines(result);
    }

    public static void main(String[] args) throws Exception {
        smilesLogP();
    }
}

