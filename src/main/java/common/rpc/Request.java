package common.rpc;

import java.util.Map;

/**
 * @author zhangxi
 * @created 13-1-17
 */
public interface Request {

    void setProvider(String provider);

    String getProvider();

    void setConsumer(String consumer);

    String getConsumer();

    void setApikey(String apikey);

    String getApikey();

    String getHost();

    String getPath();

    String getMethod();

    Map<String, Object> getParams();

    boolean needAuth();

    String getSecret();

    String getClientKey();

    Integer getTimeout();

    Integer getRetry();

    void setClientKey(String key);

    void setSecret(String secret);
}
