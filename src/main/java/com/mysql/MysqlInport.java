package com.mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomUtils;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class MysqlInport {

  private static final String sql = "/Users/xiaoman/Downloads/user.sql";
  private static final String desc = "/Users/xiaoman/Downloads/desc.txt";
  private static final String zombieSql = "/Users/xiaoman/Downloads/zombieSql.sql";
  private static BufferedReader br;

  public static List<String> getDesc() {
    try {
      String thisLine;
      br = new BufferedReader(new FileReader(new File(desc)));
      List<String> result = new ArrayList<String>();
      while ((thisLine = br.readLine()) != null) {
        result.add(thisLine);
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  public static List<User> getUser() {
    try {
      String thisLine;
      br = new BufferedReader(new FileReader(new File(sql)));
      List<User> result = new ArrayList<User>();
      while ((thisLine = br.readLine()) != null) {
        String name1 = thisLine.substring(thisLine.indexOf("name") + 5);
        String name2 = name1.substring(0, name1.indexOf(","));
        String name3 = name2.replace("'", "\'");

        String avatar1 = thisLine.substring(thisLine.indexOf("avatar") + 7);
        String avatar2 = avatar1.substring(0, avatar1.indexOf(","));

        User user = new User();
        user.setName(name3);
        user.setAvatar(avatar2);
        result.add(user);
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  public static void main(String[] args) {
    List<User> users = getUser();
    List<String> descs = getDesc();
    List<String> newDescs = getDesc();
    for (int i = 0; i < 10; i++) {
      newDescs.addAll(descs);
    }

    int beginUserName = 7121341;
    long beginId = 9900000000l;
    try {
      bw = new BufferedWriter(new FileWriter(new File(zombieSql)));
      for (int i = 0; i < users.size(); i++) {
        StringBuffer sb = new StringBuffer("insert into user(id,user_name,nick_name,pinyin,avatar,description) values (");
        sb.append(++beginId).append(",'");
        sb.append(++beginUserName).append("','");
        sb.append(users.get(i).getName()).append("','");
        sb.append(getPinyin(users.get(i).getName())).append("','");
        sb.append(users.get(i).getAvatar()).append("','");
        sb.append(newDescs.get(i)).append("');");
        // System.out.println(sb.toString());
        bw.write(sb.toString()+"\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static final Pattern pattern = Pattern.compile("[^[a-z|A-Z]+$]");
  // 表示用户是按照特殊字符开头的
  private static final String FIRST_CHARACTER = "#";
  private static BufferedWriter bw;

  public static String getFirstLetter(String str) {
    String result = PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE);
    return String.valueOf(result.charAt(0));
  }

  public static String getPinyin(String str) {
    String pinyinStr = PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE);
    String firstChar = String.valueOf(pinyinStr.charAt(0));
    if (!pattern.matcher(firstChar).find()) {
      return FIRST_CHARACTER + pinyinStr.replaceAll("[^\\w]", "");
    }
    return pinyinStr.replaceAll("[^\\w]", "");
  }

  public static int getRandomInt(int begin, int end) {
    return RandomUtils.nextInt(begin, end);
  }
}

class User {
  private String name;
  private String avatar;
  private String desc;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
