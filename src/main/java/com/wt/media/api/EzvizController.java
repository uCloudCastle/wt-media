package com.wt.media.api;

import com.winston.ssm.web.AdviceController;
import com.wt.media.domain.Server;
import com.wt.media.mapper.ServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("Ezviz")
public class EzvizController extends AdviceController {
    EzvizApi ezvizApi;

    public EzvizController(){
        this.ezvizApi=new EzvizApi();
    }

    @Autowired
    ServerMapper serverMapper;

    @GetMapping("/AccessToken")
    public EzvizApi.Token getAccessToken(@RequestParam Long serverId){
        EzvizApi.Token token;
        Server server=serverMapper.selectByPrimaryKey(serverId);
        if(server.getExpireTime()!=null && server.getExpireTime()> LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()){
            token=new EzvizApi.Token(){{
               setAccessToken(server.getAccessToken());
               setExpireTime(server.getExpireTime());
            }};
        }else{
            token=ezvizApi.getAccessToken(server.getAppKey(),server.getAppSecret());
            server.setAccessToken(token.getAccessToken());
            server.setExpireTime(token.getExpireTime());
            //获取最新的AccessToken并更新到数据库
            serverMapper.updateByPrimaryKeySelective(server);
        }

        return token;
    }
}
