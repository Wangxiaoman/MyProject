package http;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class HttpClientMain {
	
	public static void test3() throws InterruptedException, IOException {
		final RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000).setConnectTimeout(3000).build();
		final CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
				.setDefaultRequestConfig(requestConfig).build();

		httpclient.start();
		try {
			final HttpGet[] requests = new HttpGet[] {
					new HttpGet("http://www.baidu.com/"),
					new HttpGet("https://www.baidu.com/"),
					new HttpGet("http://www.baidu.com/") };
			final CountDownLatch latch = new CountDownLatch(requests.length);
			for (final HttpGet request : requests) {
				httpclient.execute(request, new FutureCallback<HttpResponse>() {
					public void completed(final HttpResponse response) {
						latch.countDown();
						System.out.println(request.getRequestLine() + "->"
								+ response.getStatusLine());
					}

					public void failed(final Exception ex) {
						latch.countDown();
						System.out.println(request.getRequestLine() + "->" + ex);
					}

					public void cancelled() {
						latch.countDown();
						System.out.println(request.getRequestLine()
								+ " cancelled");
					}
				});
			}
			latch.await();
			System.out.println("Shutting down");
		} finally {
			httpclient.close();
		}
		System.out.println("Done");
	}
	
	
	public static void test2() throws InterruptedException, ExecutionException,
			IOException {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		// Start the client
		httpclient.start();

		// Execute 100 request in async
		final HttpGet request = new HttpGet("http://www.baidu.com");
		request.setHeader("Connection", "close");
		List<Future<HttpResponse>> respList = new LinkedList<Future<HttpResponse>>();
		for (int i = 0; i < 50; i++) {
			respList.add(httpclient.execute(request, null));
		}

		// Print response code
		for (Future<HttpResponse> response : respList) {
			response.get().getStatusLine();
//			 System.out.println(response.get().getStatusLine());
		}

		httpclient.close();
	}
	
	public static void test1() throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
 
        // Execute 500 request in async
        for (int i = 0; i < 50; i++) {
            HttpGet request = new HttpGet(
                    "http://www.baidu.com");
            request.setHeader("Connection", "close");
            CloseableHttpResponse response = httpclient.execute(request);
//            System.out.println(response.getStatusLine());
            response.getStatusLine();
            response.close();
        }
 
        httpclient.close();
    }
	
	public static void main(String[] args) {
		try {
			test3();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		long aynsBeginTime = System.currentTimeMillis();
//		try {
//			test2();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		long aynsEndTime = System.currentTimeMillis();
//		
//		long beginTime = System.currentTimeMillis();
//		try {
//			test1();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		long endTime = System.currentTimeMillis();
//		
//		System.out.println(aynsEndTime-aynsBeginTime);
//		System.out.println(endTime-beginTime);
	}
}
