package com.johnny.common.utils.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by johnny01.yang on 2016/6/30.
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final int DEFAULT_TIMEOUT = 50000;

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final String DEFAULT_ACCEPT = "application/json";

    public static final int WRONG_CODE = 400;

    public static final String QUESTION_MARK = "?";

    public static final String AND = "&";

    public static final String EQUALS = "=";

    /**
     * Using Get method to execute a request
     *
     * @param path eg:http://www.baidu.com?a=1&b=2
     * @return json result
     */
    public static String executeGetMethodRequest(String path) {
        String result = null;
        HttpClient httpClient;
        HttpGet httpGet;
        try {
            URL url = new URL(path);
            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
            httpGet = new HttpGet(uri);
            httpGet.addHeader("accept", DEFAULT_ACCEPT);

            httpClient = getPooledHttpClient(DEFAULT_TIMEOUT);

            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() >= WRONG_CODE) {
                logger.warn("Http Request Failed: Error Code" + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), DEFAULT_CHARSET));
            StringBuilder s = new StringBuilder();
            while ((result = br.readLine()) != null) {
                s = s.append(result);
            }
            result = s.toString();
            br.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Using POST method to execute a request
     *
     * @param url      eg:http://www.baidu.com
     * @param paramMap paramName: paramValue
     * @return json result
     */
    public static String executePostMethodRequest(String url, Map<String, String> paramMap) {
        String result = null;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("accept", DEFAULT_ACCEPT);
            List<NameValuePair> paramList = constructNameValuePair(paramMap);
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, DEFAULT_CHARSET));
            HttpClient httpClient = getPooledHttpClient(DEFAULT_TIMEOUT);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() >= 400) {
                logger.error("Http Request Failed: Error Code" + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), DEFAULT_CHARSET));
            StringBuilder s = new StringBuilder();
            while ((result = br.readLine()) != null) {
                s = s.append(result);
            }
            result = s.toString();
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                httpPost.abort();
            }
        }
        return result;
    }

    private static List<NameValuePair> constructNameValuePair(Map<String, String> paramMap) {
        List<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return pairs;
    }

    /**
     * @param timeout : connectionTimeout | sotimeout
     */
    public static HttpClient getPooledHttpClient(int timeout) {
        RequestConfig rconf = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(rconf).build();
    }

    /**
     * 拼接URL和请求参数
     *
     * @param baseUrl  eg:http://www.baidu.com?
     * @param paramMap {a-> 1, b-> 3}
     * @return http://www.baidu.com?a=1&b=3
     */
    public static String appendRequestParam(String baseUrl, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(baseUrl))
            return null;
        if (!baseUrl.endsWith(QUESTION_MARK)) {
            baseUrl = baseUrl.concat(QUESTION_MARK);
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            baseUrl = baseUrl.concat(entry.getKey()).concat(EQUALS).concat(entry.getValue().toString()).concat(AND);
        }
        return baseUrl.substring(0, baseUrl.length() - 1);
    }

    public static void main(String[] args) {
        Map<String, Object> param = new HashMap<>();
        param.put("a", 1);
        param.put("b", 2);
        System.out.println(HttpUtils.appendRequestParam("http://www.baidu.com?", param));
    }


}
