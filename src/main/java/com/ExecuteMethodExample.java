//package com;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.AsyncClientHttpRequest;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.web.client.AsyncRequestCallback;
//import org.springframework.web.client.AsyncRestTemplate;
//import org.springframework.web.client.ResponseExtractor;
//
/**
 * springframework6上，已经对于异步的Template做了替换
 */


//@Controller
//public class ExecuteMethodExample {
//
//    public static void main(String[] args) {
//        method2();
//    }
//
//    public static void method2() {
//        ExecutorService fix = Executors.newFixedThreadPool(2);
//        fix.submit(new Test(1));
//        fix.submit(new Test(2));
//        fix.submit(new Test(3));
//        fix.submit(new Test(4));
//
//    }
//
//    public static void method1() {
//        AsyncRestTemplate asycTemp = new AsyncRestTemplate();
//        String url = "http://google.com";
//        HttpMethod method = HttpMethod.GET;
//        // create request entity using HttpHeaders
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        AsyncRequestCallback requestCallback = new AsyncRequestCallback() {
//            @Override
//            public void doWithRequest(AsyncClientHttpRequest arg0) throws IOException {
//                System.out.println(arg0.getURI());
//            }
//        };
//
//        ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>() {
//            @Override
//            public String extractData(ClientHttpResponse arg0) throws IOException {
//                return arg0.getStatusText();
//            }
//        };
//        Map<String, String> urlVariable = new HashMap<String, String>();
//        urlVariable.put("q", "Concretepage");
//        ListenableFuture<String> future =
//                asycTemp.execute(url, method, requestCallback, responseExtractor, urlVariable);
//        try {
//            // waits for the result
//            String result = future.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//class Test implements Runnable {
//    private int i;
//
//    public Test(int i) {
//        this.i = i;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("begin:" + this.i);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("end:" + this.i);
//    }
//}
