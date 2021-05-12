package com.data.fix;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

public class DrugBankSmiles {

    public static void drugbankSmiles() throws Exception {
        String fileName = "/Users/xiaomanwang/tools/开源数据/drug_smiles_r.txt";
        String fName = "/Users/xiaomanwang/tools/开源数据/drug_smiles_kv.txt";
        File file = new File(fileName);
        File newFile = new File(fName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        List<String> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            if(i == lines.size()) {
                break;
            }
            String l = lines.get(i);
            if(l.contains("--")) {
                continue;
            }
            
            if(l.contains("/drugbank/drug/name=")) {
                String name = l.replace("/drugbank/drug/name=", "");
                i++;
                if(i == lines.size()) {
                    break;
                }
                String ll = lines.get(i);
                if(StringUtils.isNotBlank(ll) ) {
                    if(ll.contains("/drugbank/drug/name=")) {
                        continue;
                    } else {
                        i++;
                        if(i == lines.size()) {
                            break;
                        }
                        String lll = lines.get(i);
                        if(lll.contains("/drugbank/drug/calculated-properties/property/value=")) {
                            String smiles = lll.replace("/drugbank/drug/calculated-properties/property/value=", "");
                            result.add(name+":"+smiles);
                            System.out.println(name+":"+smiles);
                        }
                    }
                } else {
                    break;
                }
            } else {
                continue;
            }
            
        }
        
        CharSink sink = Files.asCharSink(newFile, Charsets.UTF_8);
        sink.writeLines(result);
    }
    
    public static void main(String[] args) throws Exception {
        drugbankSmiles();
    }
}
