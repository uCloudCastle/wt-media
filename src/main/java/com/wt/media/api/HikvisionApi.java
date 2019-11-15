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


    public String getPreviewURL(String host,String appKey,String appSecret,String cameraIndexCode){
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", cameraIndexCode);
        jsonBody.put("streamType", 1);
        jsonBody.put("protocol", "rtsp");
        jsonBody.put("transmode", 1);
//        jsonBody.put("expand", "streamform=rtp");
        String body = jsonBody.toJSONString();

        String result=HikvisionNetTools.doPostStringArtemis(host, appKey, appSecret, Api.PREVIEW_URL,body);

        return  result;
    }

    public String getCaremaOnline(String host,String appKey,String appSecret,String cameraIndexCode){
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("indexCodes", new String[]{cameraIndexCode});
        jsonBody.put("pageNo",1);
        jsonBody.put("pageSize",1);
        String body = jsonBody.toJSONString();
        String result= HikvisionNetTools.doPostStringArtemis(host, appKey, appSecret, Api.CAMERA_ONLINE,body);

        return  result;
    }

    public static void main(String[] args) {
        HikvisionApi api=new HikvisionApi();
        String result = api.getPreviewURL("https://183.63.122.10:443","28039438","mbXqDip9D4RBGNGBpGVg","6cc27148558d4c379a948d94af670d88");
        System.out.println("result结果示例: " + result);

       /* String result = api.getCaremaOnline("https://183.63.122.10:443","28039438","mbXqDip9D4RBGNGBpGVg","6cc27148558d4c379a948d94af670d88");
        System.out.println("result结果示例: " + result);*/
    }
}
