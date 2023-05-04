package com.image;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageOpreation {

    public static void cutImage(String imageFileName, String outFileName, int w, int h) {
        try {
            Thumbnails.of(imageFileName).sourceRegion(0, 0, w, h).size(200, 200)
                    .toFile(outFileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void execute(int bsize) {
        String originFileName = "/Users/xiaomanwang/Downloads/1366919.jpg";
        String imageFilePath = "/Users/xiaomanwang/Downloads/testImages/";
        long btime = System.currentTimeMillis();
        for(int i=1;i<=100;i++) {
            cutImage(originFileName, imageFilePath + (bsize+i)+".png", bsize+i, bsize+i);
        }
        long etime = System.currentTimeMillis();
        System.out.println("cost ms:" + (etime - btime));
    }

    public static void main(String[] args) {
        
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{execute(100);});
        executorService.submit(()->{execute(200);});
        executorService.submit(()->{execute(300);});
        executorService.submit(()->{execute(400);});
        executorService.submit(()->{execute(500);});
        executorService.shutdown();
        
    }
}
