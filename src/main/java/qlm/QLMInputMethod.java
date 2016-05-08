package qlm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * 千里码输入法，task：7668
 * @author xiaoman
 * 
 * 
 * 千里码输入法，根据已有键盘输入样本和文本记录样本进行分析
 * 
 * 1、分析键盘输入样本，1-9分别对应的字母集合，算出分别的概率，可以过滤掉概率很低的数据
 * 2、分析文本记录的样本，分析两个字母之间的前后概率 [aa-az]分别计算出所有的概率，同样可以过滤掉概率比较低的数据
 * 3、用给出的题目进行分析，先套用第一个数字-字母概率集，列出每项可能的集合列表
 * 4、从首字母进行分析，通过概率拿出最有可能的组合，依次向后取，最终获取答案
 * 
 */





public class QLMInputMethod {

  private static final String keyboardUrl = "/Users/xiaoman/workspace/qlmdata/keyboard.txt";
  private static final String trainUrl = "/Users/xiaoman/workspace/qlmdata/train.txt";
  private static BufferedReader br;

  private static Map<String,Double> numLetter = new HashMap<>();
  private static Map<String,Double> letterLetter = new HashMap<>();
  
  private static List<String> getList(String fileUrl) {
    List<String> result = new ArrayList<String>();
    try {
      br = new BufferedReader(new FileReader(new File(fileUrl)));
      String content = "";
      do {
        content = br.readLine();
        if(StringUtils.isNotBlank(content)){
          result.add(content);
        }
      } while (StringUtils.isNoneBlank(content));
      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Collections.emptyList();
  }
  
  // 计算数字-字母的概率集合，过滤掉小于0.1的项
  private static void computeNumLetter(List<String> keyboardList){
    if(!CollectionUtils.isEmpty(keyboardList)){
      Map<String,Integer> map = new HashMap<>();
      for(String keyborad : keyboardList){
        String[] data = keyborad.split(",");
        String key = data[1]+data[0];
        int value = Integer.valueOf(data[2]);
        
        if(map.containsKey(key)){
          value = map.get(key)+value;
          map.put(key, value);
        }else{
          map.put(key, value);
        }
      }
      
      for(int num=2;num<10;num++){
        int allCount = 0;
        for(Entry<String, Integer> entry : map.entrySet()){
          if(entry.getKey().startsWith(String.valueOf(num))){
            allCount = allCount+entry.getValue();
          }
        }
        
        for(Entry<String, Integer> entry : map.entrySet()){
          if(entry.getKey().startsWith(String.valueOf(num))){
            double value = Double.valueOf(entry.getValue())/allCount;
            if(value > 0.1){
              numLetter.put(entry.getKey(), value);
            }
          }
        }
      }
    }
  }
  
  // 计算字母关系的概率集合【aa-zz】对应概率 【aa-az】的概率和为100%
  private static void computeLetterLetter(List<String> trainList){
    if(!CollectionUtils.isEmpty(trainList)){
      Map<String,Integer> map = new HashMap<>();
      for(String train : trainList){
        if(train.length()>1){
          for(int i=0;i<train.length()-1;i++){
            String key = train.charAt(i)+""+train.charAt(i+1);
            if(map.containsKey(key)){
              int value = map.get(key) + 1;
              map.put(key, value);
            }else{
              map.put(key, 1);
            }
          }
        }
      }
      
      for(char letter=97;letter<=122;letter++){
        int allCount = 0;
        for(Entry<String, Integer> entry : map.entrySet()){
          if(entry.getKey().startsWith(String.valueOf(letter))){
            allCount = allCount+entry.getValue();
          }
        }
        
        for(Entry<String, Integer> entry : map.entrySet()){
          if(entry.getKey().startsWith(String.valueOf(letter))){
            double value = Double.valueOf(entry.getValue())/allCount;
            if(value > 0.03){
              letterLetter.put(entry.getKey(), value);
            }
          }
        }
      }
      
    }
  }
  
  // 初始化
  private static void init(){
    computeNumLetter(getList(keyboardUrl));
    for(Entry<String, Double> entry : numLetter.entrySet()){
      System.out.println("2, "+entry.getKey()+":"+entry.getValue());
    }
    
    computeLetterLetter(getList(trainUrl));
    for(Entry<String, Double> entry : letterLetter.entrySet()){
      System.out.println("3 , "+entry.getKey()+":"+entry.getValue());
    }
  }
  
  // 根据输入数字，得到可能出现的字母集合
  private static List<String> getPossibleLetter(String[] inputNumbers){
    List<String> letters = new ArrayList<>();
    for(String num : inputNumbers){
      StringBuffer pStr = new StringBuffer();
      for(Entry<String, Double> entry : numLetter.entrySet()){
        if(entry.getKey().startsWith(num)){
          pStr.append(entry.getKey().substring(1));
        }
      }
      letters.add(pStr.toString());
    }
    return letters;
  }
  
  // 根据当前的字母和下一位候选字母组合，推算下一位概率最高的值
  private static String getNextLetter(String letter,String nextLetters){
    double rate = 0;
    String nextLetter = "";
    for(int i=0;i<nextLetters.length();i++){
      String nl = String.valueOf(nextLetters.charAt(i));
      if(letterLetter.containsKey(letter+nl)){
        double currentRate = letterLetter.get(letter+nl);
        if(currentRate > rate){
          rate = currentRate;
          nextLetter = nl;
        }
      }
    }
    return nextLetter;
  }
  
  public static void main(String[] args) {
    init();
    //去掉首位数字6
    String[] inputNumbers = new String[]{"3", "5", "7", "5", "3", "5", "4", "8", "6", "4", "3", "8", "6", "6", "4", "5", "2", "5", "3", "6", "6", "2", "3"};
    List<String> possibleLetters = getPossibleLetter(inputNumbers);
    System.out.println("--------------------------------");
    
    String letter = "z";//g
    for(String pLetter : possibleLetters){
      letter = getNextLetter(letter,pLetter);
      System.out.print(letter+",");
    }
    
    //z,h,i,j,i,a,o,r,e,n,s,h,e,n,g,s,i,x,i,a,n,g,x,u,
    //g,a,o,k,i,a,o,r,e,n,s,h,e,n,g,s,i,x,i,a,n,g,x,u
    
    //System.out.println(getNextLetter("z","ahuy"));
    //System.out.println(getNextLetter("g","ahuy"));
  }

}

