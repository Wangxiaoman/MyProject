package common.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import common.rpc.ErrorCode;
import common.rpc.Paging;
import common.rpc.Response;
import common.security.AuthUtil;
import common.time.TimeUtil;

/**
 * @author zhangxi
 * @created 13-1-17
 */
public class HttpClient {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    @Deprecated
    public HttpResponse executeRaw(final MtHttpRequest request) {
        final String url = request.getHost() + request.getPath();
        final String method = request.getMethod();
        URL u;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            LOG.warn(e.getMessage(), e);
            throw new HttpException(e.getMessage(), e);
        }
        String uri = u.getPath();
        HttpRequestBase httpRequest = null;
        if ("GET".equals(method)) {
            httpRequest = new HttpGet(u.toString());
        } else if ("POST".equals(method)) {
            httpRequest = new HttpPost(u.toString());
        } else if ("PUT".equals(method)) {
            httpRequest = new HttpPut(u.toString());
        } else if ("DELETE".equals(method)) {
            httpRequest = new HttpDelete(u.toString());
        }
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // override by request
        int connectTimeout = request.getTimeout() != null ? request.getTimeout() : HttpConfig.CONNECTION_TIMEOUT;
        int soTimeout = request.getTimeout() != null ? request.getTimeout() : HttpConfig.SO_TIMEOUT;
        final int retry = request.getRetry() != null ? request.getRetry() : HttpConfig.RETRY_TIME;
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount,
                                        HttpContext context) {
                if (executionCount > retry) {
                    return false;
                } else {
                    LOG.info("retry " + executionCount + " " + url + " -> "
                            + exception.getMessage());
                }
                return true;
            }
        };
        httpClient.setHttpRequestRetryHandler(myRetryHandler);
        Map<String, Object> params = request.getParams();

        if ("GET".equals(method)) {
            if (params != null && params.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    if (entry.getValue() != null) {
                        String value = String.valueOf(entry.getValue());
                        nvps.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                HttpEntity httpEntity;
                try {
                    httpEntity = new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    LOG.warn(e.getMessage(), e);
                    throw new HttpException(e.getMessage(), e);
                }
                String paramString;
                try {
                    paramString = EntityUtils.toString(httpEntity);
                } catch (IOException e) {
                    throw new HttpException(e.getMessage(), e);
                }
                String tmpUrl = httpRequest.getURI().toString();
                if (tmpUrl.contains("?")) {
                    tmpUrl += "&";
                } else {
                    tmpUrl += "?";
                }
                tmpUrl += paramString;
                try {
                    httpRequest.setURI(new URI(tmpUrl));
                } catch (URISyntaxException e) {
                    throw new HttpException(e.getMessage(), e);
                }
            }
            LOG.debug(method + " " + httpRequest.getURI());
        } else if ("POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method)) {
            Map<String, Object> entitys = request.getEntitys();
            if (entitys != null && entitys.get("json") != null) {
                String jsonString = (String) entitys.get("json");
                HttpEntity httpEntity;
                try {
                    httpEntity = new StringEntity(jsonString, "application/json", "UTF-8");
                } catch (IOException e) {
                    throw new HttpException(e.getMessage(), e);
                }
                if ("POST".equals(method)) {
                    HttpPost httpPost = (HttpPost) httpRequest;
                    httpPost.setEntity(httpEntity);
                } else if ("PUT".equals(method)) {
                    HttpPut httpPut = (HttpPut) httpRequest;
                    httpPut.setEntity(httpEntity);
                }
                LOG.debug("params: " + jsonString);
            } else if (params != null && params.size() > 0) {
                String jsonString;
                HttpEntity httpEntity;
                //ObjectMapper objectMapper = new ObjectMapper();
                try {
                    jsonString = JSON.toJSONString(params);
                    //jsonString = objectMapper.writeValueAsString(params);
                    httpEntity = new StringEntity(jsonString, "application/json", "UTF-8");
                } catch (IOException e) {
                    throw new HttpException(e.getMessage(), e);
                }
                if ("POST".equals(method)) {
                    HttpPost httpPost = (HttpPost) httpRequest;
                    httpPost.setEntity(httpEntity);
                } else if ("PUT".equals(method)) {
                    HttpPut httpPut = (HttpPut) httpRequest;
                    httpPut.setEntity(httpEntity);
                }
                LOG.debug("params: " + jsonString);
            }
            LOG.debug(method + " " + httpRequest.getURI());
        }

        // 设置Authorization
        if (request.needAuth()) {
            String clientKey = request.getClientKey();
            String secret = request.getSecret();
            String date = TimeUtil.getAuthDate(new Date());
            String authorization = AuthUtil.getAuthorization(uri, method, date, clientKey, secret);
            httpRequest.setHeader("Date", date);
            httpRequest.setHeader("Authorization", authorization);
        }

        long timeUsed = -1;
        HttpResponse response = null;
        try {
            long startTime = System.currentTimeMillis();
            response = httpClient.execute(httpRequest);
            long endTime = System.currentTimeMillis();
            timeUsed = endTime - startTime;
            LOG.debug("RESP " + httpRequest.getURI() + " (" + timeUsed + "ms)");

            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                httpRequest.abort();
                throw new HttpException(statusLine.toString());
            }
        } catch (IOException e) {
            LOG.info(httpRequest.getURI() + " -> " + e.getMessage(), e);
            httpRequest.abort();
            throw new HttpException(e.getMessage(), e);
        }
        return response;
    }

    public String execute(final MtHttpRequest request) {
        String respString;
        try {
            HttpResponse response = executeRaw(request);
            long start = System.currentTimeMillis();
            HttpEntity entity = response.getEntity();
            respString = EntityUtils.toString(entity);
            long end = System.currentTimeMillis();
            LOG.debug("EntityUtils.toString cost " + (end - start));
        } catch (IOException e) {
            throw new HttpException(e.getMessage(), e);
        }
        LOG.debug("response: " + respString);
        return respString;
    }

    public <T> MtHttpResponse<T> executeResponse(final MtHttpRequest request, Class<T> clazz) {
        String respString = execute(request);
        long start = System.currentTimeMillis();
        try {
            JSONObject nodes = JSON.parseObject(respString);
            ErrorCode error = nodes.getObject("error", ErrorCode.class);
            Paging paging = nodes.getObject("paging", Paging.class);
            if (error != null) {
                return MtHttpResponse.error(error);
            }
            T t = nodes.getObject("data", clazz);
            if (paging != null) {
                return MtHttpResponse.create(t, paging);
            }
            return MtHttpResponse.create(t);
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new HttpException("Failed to parse response", e);
        } finally {
            long end = System.currentTimeMillis();
            LOG.debug("json to Object cost " + (end - start));
        }
    }

    public <T> MtHttpResponse<T> executeResponse(final MtHttpRequest request, TypeReference<T> typeReference) {
        String respString = execute(request);
        long start = System.currentTimeMillis();
        try {
            JSONObject nodes = JSON.parseObject(respString);
            ErrorCode error = nodes.getObject("error", ErrorCode.class);
            Paging paging = nodes.getObject("paging", Paging.class);
            if (error != null) {
                return MtHttpResponse.error(error);
            }
            Object data = nodes.get("data");
            if (data == null) {
                return MtHttpResponse.error(new ErrorCode(500, "illegal response", "no data"));
            }
            T t;
            if (data instanceof JSONObject) {
                t = JSON.parseObject(((JSONObject) data).toJSONString(), typeReference);
            } else if (data instanceof JSONArray) {
                t = JSON.parseObject(((JSONArray) data).toJSONString(), typeReference);
            } else {
                t = nodes.getObject("data", (Class<T>) typeReference.getType());
            }
            if (paging != null) {
                return MtHttpResponse.create(t, paging);
            }
            return MtHttpResponse.create(t);
        } catch (Exception e) {
            LOG.info(e.getMessage(), e);
            throw new HttpException("Failed to parse response : " + respString, e);
        } finally {
            long end = System.currentTimeMillis();
            LOG.debug("json to Object cost " + (end - start));
        }
    }

    public <T> MtHttpResponse<T> executeResponse(final MtHttpRequest request, Type type) {
        String respString = execute(request);
        long start = System.currentTimeMillis();
        try {
            JSONObject nodes = JSON.parseObject(respString);
            ErrorCode error = nodes.getObject("error", ErrorCode.class);
            Paging paging = nodes.getObject("paging", Paging.class);
            if (error != null) {
                return MtHttpResponse.error(error);
            }
            Object data = nodes.get("data");
            if (data == null) {
                return MtHttpResponse.error(new ErrorCode(500, "illegal response", "no data"));
            }
            T t;
            if (data instanceof JSONObject) {
                t = JSON.parseObject(((JSONObject) data).toJSONString(), type);
            } else if (data instanceof JSONArray) {
                t = JSON.parseObject(((JSONArray) data).toJSONString(), type);
            } else {
                t = nodes.getObject("data", (Class<T>) type);
                // t = JSON.parseObject(data.toString(), type);
            }
            if (paging != null) {
                return MtHttpResponse.create(t, paging);
            }
            return MtHttpResponse.create(t);
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new HttpException("Failed to parse response : " + respString, e);
        } finally {
            long end = System.currentTimeMillis();
            LOG.debug("json to Object cost " + (end - start));
        }
    }

    public <T> Response<T> execute(final MtHttpRequest request, Type type) {
        String respString = execute(request);
        long start = System.currentTimeMillis();
        try {
            return JSON.parseObject(respString, type);
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new HttpException("Failed to parse response : " + respString, e);
        } finally {
            long end = System.currentTimeMillis();
            LOG.debug("json to Object cost " + (end - start));
        }
    }
}
