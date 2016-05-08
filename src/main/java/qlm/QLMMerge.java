package qlm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 拟合-1
 * 一元线性回归
 * @author xiaoman 最小二乘法计算线性方程
 *         http://wiki.mbalib.com/wiki/%E4%B8%80%E5%85%83%E7%BA%BF%E6%80%A7%E5%9B%9E%E5%BD%92%E9%A2%
 *         84%E6%B5%8B%E6%B3%95 
 *         
 *         b = sum（ yi ） / n - a * sum（ xi ） / n 
 *         a = （ n sum（ xiyi ） - sum（ xi ）sum（ yi ） ） / （ n * sum（ xi^2 ） - sum（xi） ^ 2 ）
 */
public class QLMMerge {

  private static final String fileUrl = "/Users/xiaoman/workspace/qlmdata/merge.txt";
  private static BufferedReader br;

  private static List<Point> getResult() {
    List<Point> points = new ArrayList<Point>();
    try {
      br = new BufferedReader(new FileReader(new File(fileUrl)));
      String content = "";
      do {
        content = br.readLine();
        if (StringUtils.isNotBlank(content)) {
          String[] list = content.split(" ");
          if (list != null) {
            double x = Double.valueOf(list[0]);
            double y = Double.valueOf(list[1]);
            points.add(new Point(x, y));
          }
        }
      } while (StringUtils.isNoneBlank(content));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return points;
  }
  
  
  private static Double getXYSum(List<Point> points){
    Double result = 0d;
    for(Point point : points){
      result = result + point.getX()*point.getY();
    }
    return result;
  }
  
  private static Double getXSum(List<Point> points){
    Double result = 0d;
    for(Point point : points){
      result = result + point.getX();
    }
    return result;
  }
  
  private static Double getXPowerSum(List<Point> points){
    Double result = 0d;
    for(Point point : points){
      result = result + Math.pow(point.getX(),2);
    }
    return result;
  }
  
  private static Double getYSum(List<Point> points){
    Double result = 0d;
    for(Point point : points){
      result = result + point.getY();
    }
    return result;
  }

  private static Double getA(List<Point> points){
    int size = points.size();
    double upNum = size*getXYSum(points)-getXSum(points)*getYSum(points);
    System.out.println("upNum:"+upNum);
    double downNum = size*getXPowerSum(points)-Math.pow(getXSum(points), 2);
    System.out.println("downNum:"+downNum);
    double a = upNum/downNum;
    return a;
  }
  
  private static Double getB(List<Point> points,double a){
    int size = points.size();
    return getYSum(points)/size - a*getXSum(points)/size;
  }
  
  public static void main(String[] args) {
    List<Point> points = getResult();
    Double a = getA(points);
    System.out.println("a:"+a);
    Double b = getB(points, a);
    System.out.println("b:"+b);
  }
}

class Point {
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  private double x;
  private double y;

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }
}
