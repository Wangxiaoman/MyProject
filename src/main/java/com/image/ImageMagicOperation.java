package com.image;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

public class ImageMagicOperation {

    public static void cropImg(String inImgPath, String outImgPath, Integer width, Integer height,
            Integer x, Integer y) throws IOException, InterruptedException, IM4JavaException {
        IMOperation operation = new IMOperation();
        operation.addImage(inImgPath);
        // 宽 高 起点横坐标 起点纵坐标
        operation.crop(width, height, x, y);
        operation.addImage(outImgPath);

        ConvertCmd cmd = new ConvertCmd(true);
        cmd.setSearchPath("/usr/local/bin");
        cmd.run(operation);
    }


    public static void main(String[] args) throws Exception {
        String filePath = "/users/xiaomanwang/data/image/seeds-eat.jpg";
        long btime = System.currentTimeMillis();
//        for (int i = 0; i < 200; i++) {
            cropImg(filePath, "/Users/xiaomanwang/data/image/dest"+ ".jpg", 100, 100, 0,
                    0);
//        }
        long etime = System.currentTimeMillis();
        System.out.println("cost ms:" + (etime - btime));

    }

}
