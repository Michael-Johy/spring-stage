package com.johnny.common.utils.http;

import com.johnny.common.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:  * This HttpUtils delegate to Apache HttpComponents to execute GET|POST request
 * and HttpMime to process multipart upload request
 *
 * @see <a href="https://hc.apache.org/">https://hc.apache.org/</a>
 * Author: johnny01.yang
 * Date  : 2016-08-22 11:47
 */
public class HttpUtils {

    private HttpUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private static final int DEFAULT_TIMEOUT = 50000;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_ACCEPT = "application/json";
    private static final int WRONG_CODE = 400;

    /**
     * Using Get method to execute a request
     *
     * @param path eg:http://www.abc.com?a=1&b=2
     * @return json result
     */
    public static String executeGetMethodRequest(String path) {
        String result = null;
        HttpGet httpGet = null;
        try {
            URL url = new URL(path);
            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(),
                    url.getQuery(), null);
            httpGet = new HttpGet(uri);
            httpGet.addHeader("accept", DEFAULT_ACCEPT);

            HttpClient httpClient = getPooledHttpClient(DEFAULT_TIMEOUT);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() >= WRONG_CODE) {
                LOGGER.warn("Http request failed-- URL:".concat(path).concat(", ErrorCode:").concat(
                        String.valueOf(response.getStatusLine().getStatusCode())));
            } else {
                result = fetchContent(response.getEntity().getContent());
            }
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != httpGet) {
                httpGet.abort();
            }
        }
        return result;
    }

    /**
     * Using POST method to execute a request
     *
     * @param path     eg:http://www.aaa.com
     * @param paramMap paramName: paramValue
     * @return json result
     */
    public static String executePostMethodRequest(String path, Map<String, String> paramMap) {
        String result = null;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(path);
            List<NameValuePair> paramList = constructNameValuePair(paramMap);
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, DEFAULT_CHARSET));
            HttpClient httpClient = getPooledHttpClient(DEFAULT_TIMEOUT);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() >= WRONG_CODE) {
                LOGGER.error("Http Request Failed: URL".concat(path).concat(",Params:").concat(paramMap.toString())
                        .concat(",Error Code").concat(String.valueOf(response.getStatusLine().getStatusCode())));
            } else {
                result = fetchContent(response.getEntity().getContent());
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != httpPost) {
                httpPost.abort();
            }
        }
        return result;
    }

    /**
     * This method is equals to {@link org.apache.http.util.EntityUtils#toString(HttpEntity, Charset)}
     *
     * @param content HttpEntity.getContent()
     * @return content
     */
    private static String fetchContent(InputStream content) {
        String result = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(content, DEFAULT_CHARSET))) {
            StringBuilder subLine = new StringBuilder("");
            String line = br.readLine();
            while (null != line) {
                subLine = subLine.append(line);
                line = br.readLine();
            }
            result = subLine.toString();
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    private static List<NameValuePair> constructNameValuePair(Map<String, String> paramMap) {
        List<NameValuePair> pairs = new ArrayList<>(paramMap.size());
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return pairs;
    }

    /**
     * @param timeout : connectionTimeout | timeout
     */
    private static HttpClient getPooledHttpClient(int timeout) {
        RequestConfig resConf = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(resConf).build();
    }

    /**
     * 拼接URL和请求参数
     *
     * @param baseUrl  eg:http://www.aaa.com?
     * @param paramMap {a-> 1, b-> 3}
     * @return http://www.aaa.com?a=1&b=3
     */
    public static String appendRequestParam(String baseUrl, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(baseUrl)) {
            return null;
        }
        StringBuilder fmtUrl = new StringBuilder(baseUrl);
        if (StringUtils.isNotBlank(baseUrl)) {
            if (!baseUrl.endsWith(CommonConstants.QUESTION_MARK)) {
                fmtUrl = fmtUrl.append(CommonConstants.QUESTION_MARK);
            }
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if (StringUtils.isBlank(entry.getKey())) {
                    continue;
                }
                String fmtValue = CommonConstants.BLANK;
                if (null != entry.getValue()) {
                    fmtValue = entry.getValue().toString();
                }
                fmtUrl = fmtUrl.append(entry.getKey()).append(CommonConstants.EQUALS)
                        .append(fmtValue).append(CommonConstants.AND);
            }
        }
        return fmtUrl.substring(0, fmtUrl.length() - 1);
    }

    /**
     * execute multipart upload
     * <p>
     * <pre>{@code
     * String path = "http://xg-file.api.vip.com/statics/file/upload";
     * Map<String, File> fileMap = new HashMap<>();
     * fileMap.put("file", new File("D:\\build.gradle"));
     * Map<String, String> paramMap = new HashMap<>();
     * paramMap.put("domain", "mxfd.vipstatic.com");
     * String responseStr = HttpUtils.executePostPartRequest(path, fileMap, paramMap);
     * }</pre>
     *
     * @param path     eg:http://www.aaa.com?
     * @param fileMap  files to upload
     * @param paramMap extra param
     * @return responseStr
     */
    public static String executePostPartRequest(String path, Map<String, List<File>> fileMap, Map<String, String> paramMap) {
        String result = null;
        HttpClient client;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(path);
            client = getPooledHttpClient(DEFAULT_TIMEOUT);

            MultipartEntityBuilder mBuilder = MultipartEntityBuilder.create();
            for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
                for (File file : entry.getValue()) {
//                    mBuilder.addPart(entry.getKey(), new FileBody(file));
                    mBuilder.addPart(entry.getKey(), new FileBody(file, ContentType.DEFAULT_BINARY, URLEncoder.encode(file.getName(), "UTF-8")));//防止文件名有中文
                }
            }
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
//                mBuilder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.APPLICATION_FORM_URLENCODED));
                mBuilder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("application/x-www-form-urlencoded", Charset.forName("UTF-8"))));
            }
            HttpEntity httpEntity = mBuilder.build();
            httpPost.setEntity(httpEntity);
            HttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() >= WRONG_CODE) {
                LOGGER.error("Http Request Failed: URL".concat(path).concat(",Params:").concat(paramMap.toString())
                        .concat(",Error Code").concat(String.valueOf(response.getStatusLine().getStatusCode())));
            } else {
                result = fetchContent(response.getEntity().getContent());
                //equals to following code snippet
                //result = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != httpPost) {
                httpPost.abort();
            }
        }
        return result;
    }

    /**
     * execute file download 获取文件输入流
     *
     * @param path request url
     * @return fileInputStream
     */
    public static InputStream executeGetPartRequest(String path) {
        HttpGet httpGet = null;
        try {
            URL url = new URL(path);
            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(),
                    url.getQuery(), null);
            httpGet = new HttpGet(uri);
//          httpGet.addHeader("accept", DEFAULT_ACCEPT);
            HttpClient httpClient = getPooledHttpClient(DEFAULT_TIMEOUT);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() >= WRONG_CODE) {
                LOGGER.error("Http Request Failed: URL".concat(path).concat(",Params:")
                        .concat(",Error Code").concat(String.valueOf(response.getStatusLine().getStatusCode())));
            } else {
                return response.getEntity().getContent();
            }
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != httpGet) {
                httpGet.abort();
            }
        }
        return null;
    }
}
