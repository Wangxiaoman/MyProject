package baidu.translate.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.vertx.core.impl.StringEscapeUtils;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20201022000596218";
    private static final String SECURITY_KEY = "cRrCjOCMY0_icFs0lCmq";

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");

        }
        return str;

    }

    public static void main(String[] args) throws Exception {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "高度600米 高度199米";
        String result = api.getTransResult(query, "auto", "en");
        System.out.println(result);
        JSONObject jo = JSON.parseObject(result);
        JSONArray transResult = jo.getJSONArray("trans_result");
        if(transResult != null) {
            System.out.println(unicodeToString(transResult.getString(0)));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String query1 = "test and try";
        result = api.getTransResult(query1, "auto", "zh");

    }

}
