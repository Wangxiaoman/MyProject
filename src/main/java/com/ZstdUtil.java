package com;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.codehaus.plexus.util.FileUtils;

import com.github.luben.zstd.Zstd;

public class ZstdUtil {
    private static SecretKeySpec spec =
            new SecretKeySpec(Base64.getDecoder().decode("zKCvVGnIphe+R8yNQwIpCb=="), "AES");

    /**
     * @param sourceString
     * @return
     */
    public static String getEncryptContext(String sourceString) {
        try {
            byte[] bytes = Zstd.compress(sourceString.getBytes(), 100000);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            byte[] random = SecureRandom.getSeed(12);
            final GCMParameterSpec ivSpec = new GCMParameterSpec(128, random);
            cipher.init(ENCRYPT_MODE, spec, ivSpec);
            byte[] bytes1 = cipher.doFinal(bytes);
            byte[] resultByte = new byte[random.length + bytes1.length];
            System.arraycopy(random, 0, resultByte, 0, random.length);
            System.arraycopy(bytes1, 0, resultByte, random.length, bytes1.length);
            return Base64.getEncoder().encodeToString(resultByte);
//            return Base64Utils.encodeToString(resultByte);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getDecryptContext(String sourceContext) {
        try {
            byte[] base64array = Base64.getDecoder().decode(sourceContext);
//            byte[] base64array = Base64Utils.decodeFromString(sourceContext);
            final byte[] iv = Arrays.copyOfRange(base64array, 0, 12);
            final byte[] pass = Arrays.copyOfRange(base64array, 12, base64array.length);
            final GCMParameterSpec ivSpec = new GCMParameterSpec(128, iv);
            final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, spec, ivSpec);
            byte[] result = cipher.doFinal(pass);
            return new String(Zstd.decompress(result, 10000000));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
 // 测试方法
    public static void main(String[] args) throws IOException {
//        StringBuffer str = new StringBuffer();
//        for(int i=0;i<10000;i++) {
//            int randomIndex = RandomUtils.nextInt(15,30);
//            StringBuffer mol = new StringBuffer();
//            for(int j=0 ; j<=randomIndex ;j++) {
//                char c = (char)RandomUtils.nextInt(65,91);
//                mol.append(c);
//            }
//            str.append(mol).append(",");
//        }
        
        String str = FileUtils.fileRead(new File("/Users/xiaomanwang/mols.txt"));
        System.out.println("originStr,"+str.substring(0, 100));
        
        long btime = System.currentTimeMillis();
        System.out.println("originStr length:"+str.length());
        String compressStr = getEncryptContext(str.toString());
        long etime = System.currentTimeMillis();
        System.out.println("zstd compress length:"+compressStr.length() + ",cost ms:"+(etime - btime));
        
        btime = System.currentTimeMillis();
        String result = getDecryptContext(compressStr);
        etime = System.currentTimeMillis();
//        System.out.println("result:"+result);
        System.out.println("zstd result cost ms:"+(etime - btime));
        
        System.out.println("zstd result:"+result.substring(0, 100));
        
        System.out.println("---------------zip----------------");
        
        
        btime = System.currentTimeMillis();
        System.out.println("originStr length:"+str.length());
        compressStr = ZipUtil.compress(str.toString());
        etime = System.currentTimeMillis();
        System.out.println("zip compress length:"+compressStr.length() + ",cost ms:"+(etime - btime));
        
        btime = System.currentTimeMillis();
        result = ZipUtil.uncompress(compressStr);
        etime = System.currentTimeMillis();
        System.out.println("zip result cost ms:"+(etime - btime));
        System.out.println("zip result:"+result.substring(0, 100));
        
        
        System.out.println("---------------smaz----------------");
        Smaz smaz = new Smaz();
        btime = System.currentTimeMillis();
        byte[] compressed = smaz.compress(str);
        String cstr = new String(compressed);
        etime = System.currentTimeMillis();
        System.out.println("smaz compress length:"+cstr.length() + ",cost ms:"+(etime - btime));
        btime = System.currentTimeMillis();
        String uncompressedString = smaz.decompress(compressed);
        etime = System.currentTimeMillis();
        System.out.println("smaz result cost ms:"+(etime - btime));
        System.out.println("smaz result:"+uncompressedString.substring(0, 100));
    }

}

