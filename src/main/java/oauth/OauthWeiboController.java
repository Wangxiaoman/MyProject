//package oauth;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import weibo4j.Oauth;
//import weibo4j.Users;
//import weibo4j.http.AccessToken;
//import weibo4j.model.User;
//import weibo4j.model.WeiboException;
//import weibo4j.org.json.JSONObject;
//
//@Controller
//@RequestMapping("/oauth/weibo/")
//public class OauthWeiboController {
//	
//	@RequestMapping(value = "/tologin", produces = "application/text;charset=utf-8")
//	public void tologin(HttpServletRequest request,HttpServletResponse response) {
//		Oauth oauth = new Oauth();
//		
//		try {
////			BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
//			response.sendRedirect(oauth.authorize("code"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@RequestMapping(value = "/afterlogin", produces = "application/text;charset=utf-8")
//	public void afterlogin(Model model,HttpServletRequest request,
//			HttpServletResponse response, String code) throws IOException {
//		response.setContentType("text/html; charset=utf-8");
//		try {
//			Oauth oauth = new Oauth();
//			AccessToken accessToken = oauth.getAccessTokenByCode(code);
//			String tokenStr = accessToken.toString();
//			
//			
////		    String str[] = tokenStr.split(","); //截取字符串，获得sccessToken和uid
////		    String accessTokenStr= str[0].split("=")[1];
////		    String str1[] = str[3].split("]");
////		    String uid = str1[0].split("=")[1];
////		    logger.info("weibo tokenStr:"+tokenStr+",uid:"+uid);
////		    
////		    获取weibo用户的昵称
////		    String url = "https://api.weibo.com/2/users/show/"+uid+".json?access_token="+accessTokenStr;
////		    String jsonStr = HttpUtils.getContent(url);
////		    logger.info("return jsonStr:"+jsonStr);
////		    
////			Users um = new Users(accessToken.getAccessToken());
////			User user = um.showUserById(uid);
////			String screenName = user.getScreenName(); //获得到用户昵称
//			
////			logger.info("weibo accessToken:"+accessTokenStr+",weibo_nickName:"+screenName);
//			
////			request.getSession().setAttribute("weibo_accessToken", tokenStr);
//			
////			Cookie cookie = new Cookie("weibo_accessToken",tokenStr);
////			cookie.setPath("/");
////			model.addAttribute("weibo_cookie_accessToken", tokenStr);
////			response.addCookie(cookie);
//			request.getSession().setAttribute("weibo_cookie_accessToken", tokenStr);
//			
//			
////			out.println("欢迎你，您已经成功登陆!<br/>");
////        	out.println("<a href='http://maslow.cn'>返回</a>");
//
//			
//			response.sendRedirect("/");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//        Users um = new Users("2.00bB5iZCVpGgBBd9fe9ba9a8sHwfaE");  
//        try {  
//            User user = um.showUserById("2360016211");  
//            System.out.println(user.toString());  
//        } catch (WeiboException e) {  
//            e.printStackTrace();  
//        }  
//	}
//}
