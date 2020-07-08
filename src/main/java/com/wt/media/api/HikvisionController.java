package com.wt.media.api;

import com.winston.ssm.web.AdviceController;
import com.wt.media.domain.Server;
import com.wt.media.mapper.ServerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Hikvision")
public class HikvisionController extends AdviceController {
    HikvisionApi hikvisionApi;
    public HikvisionController(){
        this.hikvisionApi=new HikvisionApi();
    }

    @Autowired
    ServerMapper serverMapper;

    @GetMapping("/PreviewURL")
    public String getPreviewURL(@RequestParam Long serverId,@RequestParam String code,@RequestParam(required = false) String protocol,@RequestParam(required = false)Integer streamType){
        if(streamType==null)
            streamType=0;
        Server server=serverMapper.selectByPrimaryKey(serverId);
        HikvisionApi.Protocol enumProtocol= HikvisionApi.Protocol.HLS;
        if(!StringUtils.isEmpty(protocol))
            enumProtocol= HikvisionApi.Protocol.valueOf(protocol.toUpperCase());
        return hikvisionApi.getPreviewURL(server.getHost(),server.getAppKey(),server.getAppSecret(),code, enumProtocol,streamType);
    }

    @GetMapping("/CameraOnline")
    public String getCameraOnLineStatus(@RequestParam Long serverId,@RequestParam String[] code){
        Server server=serverMapper.selectByPrimaryKey(serverId);
        return hikvisionApi.getCaremaOnline(server.getHost(),server.getAppKey(),server.getAppSecret(),code);
    }

}
