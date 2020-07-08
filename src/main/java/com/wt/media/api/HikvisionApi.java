package com.wt.media.api;

import com.alibaba.fastjson.JSONObject;
import com.wt.media.util.HikvisionNetTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikvisionApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public interface Api{
        /**
         * OpenAPI接口的上下文
         */
        String ARTEMIS_PATH = "/artemis";
        /**
         * 摄像头播发地址
         */
        String PREVIEW_URL = ARTEMIS_PATH + "/api/video/v1/cameras/previewURLs";
        /**
         * 摄像头在线状态列表
         */
        String CAMERA_ONLINE= ARTEMIS_PATH + "/api/nms/v1/online/camera/get";
    }

    public enum Protocol{
        HLS("hls"),RTSP("rtsp");

        String protocol;
        Protocol(String protocol){
            this.protocol=protocol;
        }
    }

    /**
     *
     * @param host
     * @param appKey
     * @param appSecret
     * @param cameraIndexCode
     * @param protocol
     * @param streamType  码流类型;  0 主码流 1:子码流
     * @return
     */
    public String getPreviewURL(String host,String appKey,String appSecret,String cameraIndexCode,Protocol protocol,Integer streamType){
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", cameraIndexCode);
        jsonBody.put("streamType", streamType);
        jsonBody.put("protocol", protocol.protocol);//rtsp
        jsonBody.put("transmode", 1);
//        jsonBody.put("expand", "streamform=rtp");
        String body = jsonBody.toJSONString();

        String result=HikvisionNetTools.doPostStringArtemis(host, appKey, appSecret, Api.PREVIEW_URL,body);

        return  result;
    }

    public String getCaremaOnline(String host,String appKey,String appSecret,String[] cameraIndexCode){
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("indexCodes", cameraIndexCode);
        jsonBody.put("pageNo",1);
        jsonBody.put("pageSize",cameraIndexCode.length);
        String body = jsonBody.toJSONString();
        String result= HikvisionNetTools.doPostStringArtemis(host, appKey, appSecret, Api.CAMERA_ONLINE,body);

        return  result;
    }

    public static void main(String[] args) {
        HikvisionApi api=new HikvisionApi();
      /*  String result = api.getPreviewURL("https://183.63.122.10:443","24136945","xSAoYB7OikSVXAklTSWO","6cc27148558d4c379a948d94af670d88",Protocol.HLS,0);
        System.out.println("result结果示例: " + result);*/
        String[] cameraIndexCode=new String[]{"450d7f0c1664466a8dd7bd67b90a321c","97e9dac20f4a4eb0a51586e71c8fa2b9"};
        String result = api.getCaremaOnline("https://183.63.122.10:443","24136945","daxgyVZpUpguT0F6Bcig",cameraIndexCode);
        System.out.println("result结果示例: " + result);
    }
}
