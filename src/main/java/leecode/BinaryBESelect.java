package leecode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

public class BinaryBESelect {

    private static List<KeyObject> list = new ArrayList<>();
    
    public static void main(String[] args) {
        KeyObject ko1 = new KeyObject(1,5,"1");
        KeyObject ko2 = new KeyObject(8,12,"2");
        KeyObject ko3 = new KeyObject(18,22,"5");
        KeyObject ko4 = new KeyObject(38,122,"12");
        list.add(ko1);
        list.add(ko2);
        list.add(ko3);
        list.add(ko4);
        
        System.out.println(getKey(0,3,5));
        System.out.println(getKey(0,3,15));
        System.out.println(getKey(0,3,45));
    }
    
    private static String getKey(int b, int e, int key) {
        if(b == e) {
            if(list.get(b).getBk() <= key && list.get(b).getEk() >= key) {
                return list.get(b).getValue();
            } else {
                return StringUtils.EMPTY;
            }
        }
        
        int mid = (b+e)/2;
        
        if(key < list.get(mid).getBk()) {
            return getKey(b,mid,key);
        } 
        if(key > list.get(mid).getEk()){
            return getKey(mid+1,e,key);
        }
        if(key >= list.get(mid).getBk() && key <= list.get(mid).getEk()) {
            return list.get(mid).getValue();
        }
        
        return StringUtils.EMPTY;
    }
    
    @Data
    static class KeyObject{
        private int bk;
        private int ek;
        private String value;
        
        public KeyObject(int bk, int ek, String value) {
            this.bk = bk;
            this.ek = ek;
            this.value = value;
        }
    }
}
