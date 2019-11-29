package com.wt.media.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winston.db.Result;
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

    public EzvizController() {
        this.ezvizApi = new EzvizApi();
    }

    @Autowired
    ServerMapper serverMapper;

    @GetMapping("/AccessToken")
    public Result getAccessToken(@RequestParam Long serverId) {
        EzvizApi.Token token;
        Server server = serverMapper.selectByPrimaryKey(serverId);
        if (server.getExpireTime() != null && server.getExpireTime() > LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
            token = new EzvizApi.Token() {{
                setAccessToken(server.getAccessToken());
                setExpireTime(server.getExpireTime());
            }};
        } else {
            token = ezvizApi.getAccessToken(server.getAppKey(), server.getAppSecret());
            server.setAccessToken(token.getAccessToken());
            server.setExpireTime(token.getExpireTime());
            //获取最新的AccessToken并更新到数据库
            serverMapper.updateByPrimaryKeySelective(server);
        }
        return Result.SUCCESS().setData(token);
    }

    @GetMapping("/DeviceInfo")
    public Result getDeviceInfo(@RequestParam Long serverId, @RequestParam String deviceSerial) {
        EzvizApi.Token token = getAccessToken(serverId).getData();
        Object result = this.ezvizApi.getDeviceInfo(token.getAccessToken(), deviceSerial);
        if (result != null && result instanceof JSONObject)
            return Result.SUCCESS().setData(result);

        return Result.FAIL().setMsg(result.toString());
    }

    @GetMapping("/LiveAddress")
    public Result getLiveAddress(@RequestParam Long serverId, @RequestParam String deviceSerial) {
        EzvizApi.Token token = getAccessToken(serverId).getData();
        Object result = this.ezvizApi.getLiveAddress(token.getAccessToken(), deviceSerial);
        if (result != null && result instanceof JSONArray)
            return Result.SUCCESS().setData(result);

        return Result.FAIL().setMsg(result.toString());
    }
}
