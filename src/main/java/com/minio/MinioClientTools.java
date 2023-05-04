package com.minio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import okhttp3.OkHttpClient;

public class MinioClientTools {
    private static OkHttpClient okHttpClient;
    static {
        try {
            okHttpClient = getUnsafeOkHttpClient();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
    private static OkHttpClient getUnsafeOkHttpClient() throws KeyManagementException {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
 
                        }
 
                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
 
                        }
 
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
 
            X509TrustManager x509TrustManager = (X509TrustManager) trustAllCerts[0];
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, x509TrustManager);
 
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            return builder.build();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static MinioClient minioClient =
            MinioClient.builder().endpoint("http://kv01.aws.nx.stonewise.cn:9002")
                    .httpClient(okHttpClient).credentials("uH8azUwrkeIJJITO", "RHxiH6fgTSbsuJ3OUEEjOJEoHsXGGZNl").build();

//    private static MinioClient minioClient =
//            MinioClient.builder().endpoint("http://kv01.aws.nx.stonewise.cn:9002").build();
    
    public static synchronized MinioClient getMinioClient() {
        return minioClient;
    }

    public static int putObject(String bucket, String key, String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucket) // 桶名
                    .object(key) // 上传到桶的对象名称
                    .stream(fileInputStream, fileInputStream.available(), -1).build();

            ObjectWriteResponse response = minioClient.putObject(putObjectArgs);
            System.out.println("response:" + response.toString());
            fileInputStream.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getObjectString(String bucketName, String key) {
        try {
            InputStream stream = minioClient
                    .getObject(GetObjectArgs.builder().bucket(bucketName).object(key).build());
            if (stream != null) {
                return CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static File getObject(String bucketName, String key, String fileName) {
        try {
            InputStream stream = minioClient
                    .getObject(GetObjectArgs.builder().bucket(bucketName).object(key).build());
            File file = new File(fileName);
            FileUtils.copyInputStreamToFile(stream, file);
            stream.close();
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<String> getBucketNames() {
        try {
            List<Bucket> bks = minioClient.listBuckets();
            if (!CollectionUtils.isEmpty(bks)) {
                return bks.stream().map(b -> b.name()).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
//         System.out.println(getBucketNames());
//         System.out.println(getObjectString("test-bucket", "3.sdf"));
        // MinioClient minioClient = MinioClient.builder().endpoint("http://kv01.aws.nx.stonewise.cn:9002").build();
        try {
            File file = new File("/Users/xiaomanwang/3.sdf");
            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String,String> headers = new HashMap<>();
            headers.put("content-type", "application/octet-stream");
            headers.put("Connection", "close");
            headers.put("Accept-Encoding", "identity");
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket("smiles-sdf") // 桶名
                    .object("6.sdf") // 上传到桶的对象名称
                    .headers(headers)
                    .stream(fileInputStream, fileInputStream.available(), -1).build();
            ObjectWriteResponse response = minioClient.putObject(putObjectArgs);
            System.out.println("response:" + response.toString());
            
//            minioClient.uploadObject(
//                    UploadObjectArgs.builder()
//                        .bucket("test-bucket").object("ocsr_300_time.txt").filename("/Users/xiaomanwang/data/ocsr_300_time.txt").build());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // System.out.println(putObject("test-bucket", "product.json",
        // "/Users/xiaomanwang/data/product.json"));
        // System.out.println(getObjectString("test-bucket", "rankHopping.sdf"));

    }
}
