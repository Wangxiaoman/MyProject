package qlm;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class QLMSplider3 {

  public static final String QLM_SPLIDER_3_PRE_URL = "http://www.qlcoder.com/train/spider3/";

  public static Document getDoc(int index) throws Exception {
    String url = QLM_SPLIDER_3_PRE_URL + index;
    try {
      Document doc = Jsoup.connect(url).get();
      return doc;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /**
   * 解析
   * @param doc
   * @return
   */
  public static String getContent(Document doc) {
    return doc.body().text();
  }

  public static int getUpdateTime(int index) throws Exception {
    Document doc = getDoc(index);
    String content = getContent(doc);
    // System.out.println("content:"+content);
    int i = 1;
    while (true) {
      Thread.sleep(15 * 1000);
      Document cdoc = getDoc(index);
      if (cdoc != null) {
        String currentContent = getContent(cdoc);
        if (!content.equals(currentContent)) {
          System.out.println("content:" + content + ",currentContent:" + currentContent);
          return i * 15;
        } else {
          i++;
        }
      }
    }
  }
  
  public static void run(int i){
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println("index"+i+"start !");
          System.out.println("index:" + i + ",update second:" + getUpdateTime(i));
          System.out.println("index next:" + i + ",update second:" + getUpdateTime(i));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      
    }).start();
  }

  public static void main(String[] args) throws IOException {
    try {
      run(3);
      run(4);
      run(6);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
