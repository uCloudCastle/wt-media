package com.wt.media.util;

import com.hikvision.artemis.sdk.Client;
import com.hikvision.artemis.sdk.Request;
import com.hikvision.artemis.sdk.Response;
import com.hikvision.artemis.sdk.constant.Constants;
import com.hikvision.artemis.sdk.enums.Method;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 海康流媒体服务器网络工具类
 */
public class HikvisionNetTools {
    private static final Logger logger = LoggerFactory.getLogger(HikvisionNetTools.class);

    /**
     *
     * @param host   海康流媒体服务器地址
     * @param appKey
     * @param appSecret
     * @param apiUrl  Api接口地址
     * @param body
     * @param querys
     * @param accept
     * @param contentType
     * @param header
     * @return
     */
    public static String doPostStringArtemis(String host, String appKey, String appSecret, String apiUrl, String body, Map<String, String> querys, String accept, String contentType, Map<String, String> header) {
        String responseStr=null;
        try {
            Map<String, String> headers = new HashMap();
            if (StringUtils.isNotBlank(accept)) {
                headers.put("Accept", accept);
            } else {
                headers.put("Accept", "*/*");
            }

            if (StringUtils.isNotBlank(contentType)) {
                headers.put("Content-Type", contentType);
            } else {
                headers.put("Content-Type", "application/text;charset=UTF-8");
            }

            if (header != null) {
                headers.putAll(header);
            }
            Request request = new Request(Method.POST_STRING, host, apiUrl, appKey, appSecret, Constants.DEFAULT_TIMEOUT);
            request.setHeaders(headers);
            request.setQuerys(querys);
            request.setStringBody(body);
            Response response = Client.execute(request);
            responseStr = getResponseResult(response);
        } catch (Exception var11) {
            logger.error("the Artemis PostString Request is failed[doPostStringArtemis]", var11);
        }

        return responseStr;
    }

    public static String doPostStringArtemis(String host, String appKey, String appSecret, String apiUrl, String body){
        String contentType = "application/json";
        return doPostStringArtemis(host,appKey,appSecret,apiUrl,body,null,null,contentType,null);
    }

    private static String getResponseResult(Response response) {
        String responseStr = null;
        int statusCode = response.getStatusCode();
        if (!String.valueOf(statusCode).startsWith("2") && !String.valueOf(statusCode).startsWith("3")) {
            String msg = response.getErrorMessage();
            responseStr = response.getBody();
            logger.error("the Artemis Request is Failed,statusCode:" + statusCode + " errorMsg:" + msg);
        } else {
            responseStr = response.getBody();
            logger.info("the Artemis Request is Success,statusCode:" + statusCode + " SuccessMsg:" + response.getBody());
        }

        return responseStr;
    }
}
