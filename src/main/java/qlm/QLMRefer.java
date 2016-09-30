package qlm;

import java.util.HashMap;
import java.util.Map;

import com.ecyrd.speed4j.StopWatch;

import http.HttpClientUtils;
import http.Request;
import http.Response;

/**
 * 代码提交500，用curl
 * 
 * curl --cookie "uuid=571c2968769d3; remember_82e5d2c56bdd0811318f0cf078b78bfc=eyJpdiI6IklWa0tVbjdYcmwrQzRIY3plcjVzbEE9
 * PSIsInZhbHVlIjoiN0xhTTUyUVZDVFRVVThjcndIOHJ4Tjk1NWFuMnJrb3VTdFFybE1tM1VQN1ZEbWl6QnpcL2h0N0RzZUc4U05kT0tucjd0SGhlMWhQX
 * C9MY29uT0YrdU4rdFwvbjY0ZUxTQXRwMU9wbURVSHVOYzg9IiwibWFjIjoiMjcxOTEyNDY0OTQ0ODU4M2E4NGUyZTEzZjA2ZGYyMzA1NWE1ZDJhODJkMGJ
 * jMjZhMDg2NDkxZGVjMTI0OWMxYiJ9; XSRF-TOKEN=eyJpdiI6ImxDb2ZpZXZUZHNGTEo2ajFNVjFqVkE9PSIsInZhbHVlIjoiRmIwajdnNzd5MGlpNmha
 * Sm1cLzU3NW5rSTMrUmNTWjJyQVZjSVBtZW1BNDl6SXduaVFEdVwvUk5CcXVHXC9TUHl4Nzl2KzZnK05SOWJxRTl6VmhURWJwU1E9PSIsIm1hYyI6IjdlOT
 * kzYzVkYmYwODM0M2JmNzMyOTYwMWNkMGMxNTMyMjg3MGZjNWIzYWViYTc0Zjk4MzAyYjJlMjNhMWFmZWUifQ%3D%3D; laravel_session=eyJpdiI6Ij
 * FEZUp2XC9obEs0YWNWWVdQcVwvQ1Rhdz09IiwidmFsdWUiOiJGQlZzZVBJS0RydTVpM1hnUjJXRGFENTVhTEh3UTBPM3c3ODcrMHZWXC9vc3JHbTBJejRP
 * a2hTN05jRVNRMWdCblpSTEhCK2dTYm1udXdHcjBpMjEzU1E9PSIsIm1hYyI6IjNhMTMzNDdjMmY2NjUzODM5MmQ2Mjk2NjY2YWUwMWU5ZGNlOGYyZTkxMj
 * AwNmY0ODE5MzJjNDdjNmEyMDc2NTEifQ%3D%3D; è¿é¢çç­æ¡æ¯oreo=eyJpdiI6IjBUekpPZXRxaUlFNHlOMmkwRmVzaEE9PSIsInZhbHVlIjoiajFcLzE
 * 4c3lVOXJ1Y1YzTUNRTllpeGc9PSIsIm1hYyI6IjVkZmJmMDhiYzlmZmY3NzYzNTNjODljNmY4OTk2NmZlODI4OTI5ZDY5ZDI0Y2UxMDQ3ZjRmMzBjY2VlZ
 * mMyNzMifQ%3D%3D; Hm_lvt_420590b976ac0a82d0b82a80985a3b8a=1461463404; Hm_lpvt_420590b976ac0a82d0b82a80985a3b8a=1461463426"
 *  --referer "http://cpc.people.com.cn/" -d "_token=QJZFoEDPnzQvcRr0UTd6bHKzkjls8PkeNZSzMSsY&answer=referer" 
 *  "http://www.qlcoder.com/task/17/solve"
 * 
 * 
* @ClassName: QLMRefer 
* @Description: TODO
* @author xiaoman 
* @date 2016年4月24日 上午10:41:57 
*
 */
public class QLMRefer {
  private static void post(){
    String url = "http://www.qlcoder.com/task/17/solve";
    String cookie = "uuid=571c2968769d3; remember_82e5d2c56bdd0811318f0cf078b78bfc=eyJpdiI6IklWa0tVbjdYcmwrQzRIY3plcjVzbEE9PSIsInZhbHVlIjoiN0xhTTUyUVZDVFRVVThjcndIOHJ4Tjk1NWFuMnJrb3VTdFFybE1tM1VQN1ZEbWl6QnpcL2h0N0RzZUc4U05kT0tucjd0SGhlMWhQXC9MY29uT0YrdU4rdFwvbjY0ZUxTQXRwMU9wbURVSHVOYzg9IiwibWFjIjoiMjcxOTEyNDY0OTQ0ODU4M2E4NGUyZTEzZjA2ZGYyMzA1NWE1ZDJhODJkMGJjMjZhMDg2NDkxZGVjMTI0OWMxYiJ9; XSRF-TOKEN=eyJpdiI6ImxDb2ZpZXZUZHNGTEo2ajFNVjFqVkE9PSIsInZhbHVlIjoiRmIwajdnNzd5MGlpNmhaSm1cLzU3NW5rSTMrUmNTWjJyQVZjSVBtZW1BNDl6SXduaVFEdVwvUk5CcXVHXC9TUHl4Nzl2KzZnK05SOWJxRTl6VmhURWJwU1E9PSIsIm1hYyI6IjdlOTkzYzVkYmYwODM0M2JmNzMyOTYwMWNkMGMxNTMyMjg3MGZjNWIzYWViYTc0Zjk4MzAyYjJlMjNhMWFmZWUifQ%3D%3D; laravel_session=eyJpdiI6IjFEZUp2XC9obEs0YWNWWVdQcVwvQ1Rhdz09IiwidmFsdWUiOiJGQlZzZVBJS0RydTVpM1hnUjJXRGFENTVhTEh3UTBPM3c3ODcrMHZWXC9vc3JHbTBJejRPa2hTN05jRVNRMWdCblpSTEhCK2dTYm1udXdHcjBpMjEzU1E9PSIsIm1hYyI6IjNhMTMzNDdjMmY2NjUzODM5MmQ2Mjk2NjY2YWUwMWU5ZGNlOGYyZTkxMjAwNmY0ODE5MzJjNDdjNmEyMDc2NTEifQ%3D%3D; è¿é¢çç­æ¡æ¯oreo=eyJpdiI6IjBUekpPZXRxaUlFNHlOMmkwRmVzaEE9PSIsInZhbHVlIjoiajFcLzE4c3lVOXJ1Y1YzTUNRTllpeGc9PSIsIm1hYyI6IjVkZmJmMDhiYzlmZmY3NzYzNTNjODljNmY4OTk2NmZlODI4OTI5ZDY5ZDI0Y2UxMDQ3ZjRmMzBjY2VlZmMyNzMifQ%3D%3D; Hm_lvt_420590b976ac0a82d0b82a80985a3b8a=1461463404; Hm_lpvt_420590b976ac0a82d0b82a80985a3b8a=1461463426";
    String referer = "http://cpc.people.com.cn/";
    
    Request request = new Request();
    request.setUrl(url);
    request.setCookies(cookie);
    Map<String,String> headers = new HashMap<>();
    headers.put("Referer", referer);
    headers.put("Content-Type", "application/x-www-form-urluncoded");
    request.setHeaders(headers);
    
    Map<String,String> params = new HashMap<>();
    params.put("answer", "referer");
    params.put("_token", "QJZFoEDPnzQvcRr0UTd6bHKzkjls8PkeNZSzMSsY");
    request.setParameters(params);
    
    try {
      Response response = HttpClientUtils.postInvoke(request);
      System.out.println(response.getStringResult());
      System.out.println(response.getResponseStatusCode());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static void main(String[] args) {
	  StopWatch s = new StopWatch();
	  s.start();
	  s.setTag("one method");
//    post();
	  test();
    s.stop();
    System.out.println(s);
  }
  
  public static void test(){
	  System.out.println("123123");
  }

}
