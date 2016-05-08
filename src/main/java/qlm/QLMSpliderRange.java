package qlm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import http.HttpClientUtils;
import http.Request;

public class QLMSpliderRange {

  public static final String QLM_FILE_DOWN_URL = "http://www.qlcoder.com/download/hugefile";
  

  public static void main(String[] args) throws IOException {
    try {
      Request request = new Request();
      request.setUrl(QLM_FILE_DOWN_URL);
      Map<String,String> headers = new HashMap<>();
      headers.put("RANGE", "bytes=12345678901-12345678999");
      request.setHeaders(headers);
      
      System.out.println(HttpClientUtils.getInvoke(request).getStringResult());
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
