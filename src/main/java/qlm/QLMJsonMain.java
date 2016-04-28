package qlm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 千里码，json
* @ClassName: JsonMain 
* @Description: TODO
* @author xiaoman 
* @date 2016年4月23日 下午3:22:29 
*
 */
public class QLMJsonMain {
  public static void main(String[] args) {
    JSONObject jo = new JSONObject();
    
    jo.put("name", "Sayalic");
    jo.put("age", 25);
    jo.put("girlfriend", null);
    
    JSONObject friend = new JSONObject();
    friend.put("age", 24.5);
    friend.put("name", "dploop");
    JSONArray ja = new JSONArray();
    ja.add("pear");
    ja.add("lemon");
    friend.put("FavoriteFruits", ja);
    
    jo.put("gayfriend", friend);
    
    JSONArray jafruits = new JSONArray();
    jafruits.add("orange");
    jafruits.add("banana");
    jafruits.add("apple");
    jo.put("FavoriteFruits", jafruits);
    
    System.out.println(jo.toJSONString());
  }
}
