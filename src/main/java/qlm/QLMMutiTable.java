package qlm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import http.Request;
import http.Response;
//task 7671

// 解题思路：
// 前提：将数据查出写入了自定义的表中，表中包含来源表table_num，查询的序列值number
// 1、因为需要获取第23333页的数据，其实就是offset为466640,limit为20的数据
// 2、如果数据平均分布的话，那么从每张表的46400为起点，向后取几百条出来，存到库中
// 3、分析库中的数据，因为最初库里的数据也就只要几千条，利用二分找到一个相近的数字resultNum
// 4、先试着找sum(max(num)) 最接近46400的数据  select sum(num) from (select table_num,max(number) num from items where favs>resultNum group by table_num) as temp;
// 5、找到这个数字，再去校验，是否前后都是有足够每张表的值
// 5、使用的sql  select table_num,max(number) num from items where favs>resultNum group by table_num;（检查是否每张表都有数据存在）
// 6、使用的sql  select table_num,min(number) num from items where favs<resultNum group by table_num;（检查是否每张表都有数据存在）
// 7、如果上面校验的有哪张表没有数据，那么再相应对这张表向前向后取一些数据，反复这个过程，最终定位

public class QLMMutiTable {
  private static final String driver = "com.mysql.jdbc.Driver";
  private static final String url = "jdbc:mysql://localhost:3306/test";
  private static final String user = "test";
  private static final String password = "test";

  // table=5&a=100&b=30
  private static final String REQUEST_URL_PRIFIX = "http://www.qlcoder.com/train/mysql?";

  private static Connection getConnection(String driver, String url, String user, String password) {
    Connection conn = null;
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

  private static void closeConnection(Connection connection) {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void saveData(int table, int offset, int limit, Connection conn) {
    Request request = new Request();
    request.setSocketTimeout(10000000);
    request.setUrl(REQUEST_URL_PRIFIX + "table=" + table + "&a=" + offset + "&b=" + limit);
    try {
      Response response = http.HttpClientUtils.getInvoke(request);
      if (response.getResponseStatusCode() == 200) {
        JSONObject jo = JSONObject.parseObject(response.getStringResult());
        JSONArray ja = jo.getJSONArray("data");
        System.out.println("table:" + table + ",offset:" + offset + ",size=" + ja.size());
        for (int i = 0; i < ja.size(); i++) {
          JSONObject item = ja.getJSONObject(i);
          int id = item.getIntValue("id");
          int favs = item.getIntValue("favs");
          int num = offset + i + 1;

          String sql = "insert into items values(" + id + "," + favs + "," + table + "," + num + ")";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.execute();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void saveAllData() {
    int limit = 100;
    Connection conn = getConnection(driver, url, user, password);
    System.out.println("create connection ");
    for (int i = 0; i < 10; i++) {
      System.out.println("table name is:"+i);
      int offset = 46400;
      int jmax = (i==5?10:4);
      for (int j = 0; j < jmax; j++) {
        offset = offset+limit;
        System.out.println("offset is:"+offset);
        saveData(i, offset, limit, conn);
      }
    }
    closeConnection(conn);
  }

  public static void main(String[] args) {
    saveAllData();
  }

}
