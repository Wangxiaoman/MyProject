package qlm;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class QLMScreen {
    public static File generateSnapshot() {
        //File snapshotFile = new File("D:/" + System.currentTimeMillis() + ".jpg");
        File snapshotFile = new File("D:/snapshot.jpg");
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        try {
            Robot robot;
            robot = new Robot();
            BufferedImage image = robot.createScreenCapture(new Rectangle(width, height)); 
            ImageIO.write(image, "jpg", snapshotFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return snapshotFile;  
    }
    
    /**
     * 取得图像上指定位置像素的 rgb 颜色分量。
     *
     * @param image 源图像。
     * @param x 图像上指定像素位置的 x 坐标。
     * @param y 图像上指定像素位置的 y 坐标。
     * @return 返回包含 rgb 颜色分量值的数组。元素 index 由小到大分别对应 r，g，b。
     */
    public static int[] getRGB(BufferedImage image, int x, int y) {
        int[] rgb = null;
 
        if (image != null && x < image.getWidth() && y < image.getHeight()) {
            rgb = new int[3];
            int pixel = image.getRGB(x, y);
            rgb[0] = (pixel & 0xff0000) >> 16;
            rgb[1] = (pixel & 0xff00) >> 8;
            rgb[2] = (pixel & 0xff);
        }
 
        return rgb;
    }
 
    /**
     * 将RGB转换为16进制Hex
     *
     * @param r red颜色分量
     * @param g green颜色分量
     * @param b blue颜色分量
     * @return
     */
    public static String toHex(int r, int g, int b) {
        return "#" + toHexValue(r) + toHexValue(g) + toHexValue(b);
    }
 
    private static String toHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
    
    public static void printPointRGB(int x, int y) {
        try {
            BufferedImage bi = ImageIO.read(new File("/Users/xiaoman/Downloads/145303100168558.png"));
            int[] rgb = getRGB(bi, x, y);
            Color color = new Color(rgb[0], rgb[1], rgb[2]);
            System.out.println("red = " + color.getRed());
            System.out.println("green = " + color.getGreen());
            System.out.println("blue = " + color.getBlue());
            System.out.println("hex = " + toHex(color.getRed(), color.getGreen(), color.getBlue()));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void changeRGB() throws Exception{
    	BufferedImage image = ImageIO.read(new File("/Users/xiaoman/Downloads/145303100168558.png"));
    	for(int x=0;x<image.getWidth();x++){
    		for(int y=0;y<image.getHeight();y++){
    			int[] rgb = new int[3];
                int pixel = image.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                
                if(rgb[0] % 2 == 1){
                	image.setRGB(x, y, 0xff0000);
                }else{
                	image.setRGB(x, y, 0);
                }
        	}
    	}
    	
        ImageIO.write(image, "png", new File("/Users/xiaoman/Downloads/output.png"));
    }
    
    public static void main(String[] args) throws Exception {
//        generateSnapshot();
//        printPointRGB(100, 100);
    	
    	changeRGB();
    }
}
