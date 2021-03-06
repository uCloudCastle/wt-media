package com.wt.media.api;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

/**
 * 萤石云API接口
 */
public class EzvizApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public interface Api{
        /**
         * 获取萤石云Token API接口
         */
        String ACCESS_TOKEN ="https://open.ys7.com/api/lapp/token/get";

        String DEVICE_INFO="https://open.ys7.com/api/lapp/device/info";

        String LIVE_ADDRESS="https://open.ys7.com/api/lapp/live/address/get";
    }

    public static class Token{
        String accessToken;
        long expireTime;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }
    }

    RestTemplate restTemplate;
    public EzvizApi(){
        this.restTemplate=new RestTemplate();
    }

    public EzvizApi(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public Token getAccessToken(String appKey,String appSecret){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("appKey", appKey);
        map.add("appSecret",appSecret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        JSONObject result=restTemplate.postForObject(Api.ACCESS_TOKEN,request, JSONObject.class);
        logger.info(Api.ACCESS_TOKEN +"\t\t"+result.toJSONString());

        if(result.getString("code").equals("200")){
            JSONObject data=result.getJSONObject("data");
            Token token=new Token();
            token.setAccessToken(data.getString("accessToken"));
            token.setExpireTime(data.getLong("expireTime"));

            return token;
        }
        return null;
    }

    public Object getDeviceInfo(String accessToken,String deviceSerial){
        if(StringUtils.isEmpty(deviceSerial))
            return null;
        deviceSerial=deviceSerial.substring(0,deviceSerial.indexOf("/"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("accessToken", accessToken);
        map.add("deviceSerial",deviceSerial);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        JSONObject result=restTemplate.postForObject(Api.DEVICE_INFO,request, JSONObject.class);
        logger.info(Api.ACCESS_TOKEN +"\t\t"+result.toJSONString());

        if(result.getString("code").equals("200")){
            JSONObject data=result.getJSONObject("data");
            return data;
        }
        return result.getString("msg");
    }

    public Object getLiveAddress(String accessToken,String deviceSerial){
        if(StringUtils.isEmpty(deviceSerial))
            return null;

        deviceSerial=deviceSerial.replace('/',':');
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("accessToken", accessToken);
        map.add("source",deviceSerial);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        JSONObject result=restTemplate.postForObject(Api.LIVE_ADDRESS,request, JSONObject.class);
        logger.info(Api.ACCESS_TOKEN +"\t\t"+result.toJSONString());

        if(result.getString("code").equals("200")){
            return result.getJSONArray("data");
        }
        return result.getString("msg");
    }


    public static void main(String[] args) throws IOException {
        long l=LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        EzvizApi api=new EzvizApi();
        Token token= api.getAccessToken("a5cba0b45f004a159d660734fdfcf2cc","5155c81fef6acdd2dde53a43db9bb7dd");
        //   "D55911814/1"
        Object result=api.getLiveAddress(token.getAccessToken(),"D12891375/1");
        System.in.read();
    }
}
