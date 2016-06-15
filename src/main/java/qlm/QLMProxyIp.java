package qlm;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class QLMProxyIp {
  public static void main(String[] args) throws Exception {
    System.getProperties().setProperty("proxySet", "true");
    System.getProperties().setProperty("http.proxyHost", "121.201.63.168");
    System.getProperties().setProperty("http.proxyPort", "8080");

    HttpURLConnection connection = (HttpURLConnection) new URL("http://www.qlcoder.com/train/proxy").openConnection();
    connection.setConnectTimeout(6000); // 6s
    connection.setReadTimeout(6000);
    connection.setUseCaches(false);

    if (connection.getResponseCode() == 200) {
      System.out.println("使用代理IP连接网络成功");
      System.out.println(connection.getResponseMessage());

      BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
      ByteArrayOutputStream byteout = new ByteArrayOutputStream();
      // 用OutputStream来接收
      byte bb[] = new byte[1024];
      int length = 0;
      while ((length = in.read(bb, 0, bb.length)) != -1) {
        byteout.write(bb, 0, length);
      }
      // 用文本方式来接收
      System.out.println(byteout.toString());
      in.close();
      byteout.close();
    }
  }
}
