package qlm;

import http.HttpClientUtils;
import http.Request;
import http.Response;

import java.security.MessageDigest;

public class QLMFresh {

  private static void fresh(String checkCode) throws Exception {

    Request request = new Request();
    String url = "http://www.qlcoder.com/train/handsomerank?_token=aMDmssHi8MEMuP89iIEMRyegS585qxjGBAKq4FRA&user=simmer&checkcode=" + checkCode;
    request.setUrl(url);
    request
        .setCookies("remember_82e5d2c56bdd0811318f0cf078b78bfc=eyJpdiI6IjlydW1tcEhqRGdjRHNHU2tYaU91Mnc9PSIsInZhbHVlIjoiWmFDeTV4YnpLeHRxM3NDa0g5bXA0OXZNRDdoQTdhR3dBR2xmV041QkRPMXQ2M0F0OTJoaFhpTTh0OGNBMWp5NTVkNnFjRURvKzJtSzMrZ09keHlReVJqS3NcL1JRVUhjcHEzdnVOZWpyRXFZPSIsIm1hYyI6IjY2ZjE1Y2U5Y2ZhZmE1MTc0ZTdiZjk0MDhlODc1OGJhMTY1YTNiMjdmZDZlYjcxYzVjNzFhYjEyZGI5MzdlNjIifQ%3D%3D; uuid=571a1f3c72767; è¿é¢çç­æ¡æ¯oreo=eyJpdiI6ImlWM0xPVTZBNDhKNGtJVTFUVXJHZHc9PSIsInZhbHVlIjoiVE9RUU5EaFM3VExhR0laSlhYNVZaUT09IiwibWFjIjoiZDNkYjZlMWJhMzg0ZGM1OThmMzE2ZTA5YWNjZTRiYzUwMjZkZDIwZjI5YmZmMzQzNTI0ZTE2ZWJjMTRjMjhjMCJ9; XSRF-TOKEN=eyJpdiI6Im5lczAwTjdHZk5jUVZqRGdZVTR2SUE9PSIsInZhbHVlIjoiQ2xyQmhNTGlWbXIxcmZvWXJnTnFMV2ZOQlpESjd6OU5JOWVCZVNBRlwvRXZEZEs2OG95SjV0alJVXC8wdDhSWHRcLzlPa1J5T3ZDa01EeXh0YzlJMDl3VVE9PSIsIm1hYyI6IjdkMGI3YzBmOWUyYjFmYjAwZDY2NzljMmQxYjQ5Y2JhZGY2NGNhYWMzZWZmZTBkNTgzMWY3MTU0NTIzODVkNDgifQ%3D%3D; laravel_session=eyJpdiI6ImRoUjRIamxneHZIcDFzVVk3ZnQ3S0E9PSIsInZhbHVlIjoieUFKbFkxZThucWtDVnNKamNtTFZsS0VqdVFlSWpMdzhoYkNSRFo2MXZ0aFpseTU2b1hLMG5LbHhHMitXTFRlcStUVlVZSVN4SWhrRXAzWjhyZHozN0E9PSIsIm1hYyI6Ijk3MGM0NTM4OGNjMGI1MDI3ZDg4Njk4NDM3NDIwZDBkMWY0MzhiMzljMzA2ZGM4NThlM2Q4Mjk0ZTI1MTAyNDAifQ%3D%3D; Hm_lvt_420590b976ac0a82d0b82a80985a3b8a=1461329714; Hm_lpvt_420590b976ac0a82d0b82a80985a3b8a=1461396100");

    Response response = HttpClientUtils.getInvoke(request);

    System.out.println(response.getResponseStatusCode());
  }

  private static int getCheckCode(int successNum) throws Exception {
    int beginCode = 121123;
    while (true) {
      String preCode = "20160423simmer" + successNum + "" + ++beginCode;
      String md5Str = getMd5(preCode);
      System.out.println("preCode："+preCode);
      System.out.println("md5Str:" + md5Str);
      if (md5Str.startsWith("000000")) {
        return beginCode;
      }
    }
  }

  private static String getMd5(String strObj) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    // md.digest() 该函数返回值为存放哈希值结果的byte数组
    return byteToString(md.digest(strObj.getBytes()));
  }

  private static String byteToString(byte[] bByte) {
    StringBuffer sBuffer = new StringBuffer();
    for (int i = 0; i < bByte.length; i++) {
      sBuffer.append(byteToArrayString(bByte[i]));
    }
    return sBuffer.toString();
  }

  private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

  // 返回形式为数字跟字符串
  private static String byteToArrayString(byte bByte) {
    int iRet = bByte;
    // System.out.println("iRet="+iRet);
    if (iRet < 0) {
      iRet += 256;
    }
    int iD1 = iRet / 16;
    int iD2 = iRet % 16;
    return strDigits[iD1] + strDigits[iD2];
  }

  public static void main(String[] args) {
    try {
      System.out.println(getCheckCode(2));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
