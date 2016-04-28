package qlm;

import com.util.MD5;

public class QLMCode {

  public static void getBirthDay(String md5) {
    int beginYear = 1900;
    int endYear = 2016;
    int beginMonth = 1;
    int endMonth = 12;
    int beginDay = 1;
    int endDay = 31;

    for (int year = beginYear; year <= endYear; year++) {
      for (int month = beginMonth; month <= endMonth; month++) {
        for (int day = beginDay; day <= endDay; day++) {
          String monthStr = month+"";
          if(month<10){
            monthStr = "0"+month;
          }
          String dayStr = day+"";
          if(day<10){
            dayStr = "0"+day;
          }
          String birth = year + "" + monthStr +  dayStr;
//          System.out.println("birth:"+birth);
          String birthMd5 = MD5.getMD5Code(birth);
          if (md5.equals(birthMd5)) {
            System.out.println("birth:" + birth);
            break;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    String md5 = "7E38890B870934B126F66857ED6B57B9".toLowerCase();
    getBirthDay(md5);
//    
//    System.out.println(MD5.getMD5Code("19850101"));
  }
}
