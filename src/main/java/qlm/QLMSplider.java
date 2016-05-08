package qlm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QLMSplider {

  public static final String DOUBAN_MOVIE_TOP250_URL = "https://movie.douban.com/top250";
  
  public static Document getDoc(int start) throws Exception{
    String url = DOUBAN_MOVIE_TOP250_URL+"?start="+start;
    Document doc = Jsoup.connect(url).get();
    return doc;
  }

  /**
   * 解析
   * @param doc
   * @return
   */
  public static List<Double> getContent(Document doc,boolean lastPage) {
    Elements links = doc.select(".rating_num");
    List<Double> result = new ArrayList<Double>();
    
    int num = 0;
    Iterator<Element> iterator = links.iterator();
    while (iterator.hasNext()) {
      Element e = iterator.next();
      System.out.println(e.text());
      result.add(Double.valueOf(e.text()));
      if(++num == 16 && lastPage){
        break;
      }
    }
    return result;
  }
  
  
  public static void getAvgNum() throws Exception{
    int pageSize = 25;
    Double num = 0d;
    for(int i=0;i<7;i++){
      System.out.println("page num:"+i);
      Document doc = getDoc(i*pageSize);
      List<Double> scores = getContent(doc,i==6?true:false);
      System.out.println("page size:"+scores.size());
      for(Double score : scores){
        num = num + score;
      }
    }
    System.out.println("num:"+num);
  }


  public static void main(String[] args) throws IOException {
    try {
      getAvgNum();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
