package com;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastjsonTest {

    public static void main(String[] args) {
//        String jsonStr = "{\"root\":{\"nodes\":[{\"ref\":\"mol0\"}]}}";
//        String jsonStr = "{\"$root\":{\"$nodes\":[{\"ref\":\"mol0\"}]}}";
//        String jsonStr = "{\"$root\":[{\"ref\":\"mol0\"}]}";
//        String jsonStr = "{\"$root\":[{\"$ref\":\"mol0\"}]}";
//        String jsonStr = "{\"$root\":[{\"$tef\":\"mol0\"}]}";
//        String jsonStr = "[{\"$ref\":\"mol0\"}]";
        String jsonStr = "{\"root\":{\"$ref\":\"212\"}}";
//        JSONArray json =JSONObject.parseArray(jsonStr);
        JSONObject json =JSONObject.parseObject(jsonStr);
        System.out.println(json.toJSONString());
        
    }
}
