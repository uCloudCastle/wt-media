package com.wt.media.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * Created by winston on 2019-11-13.
 */
 @JsonInclude(JsonInclude.Include.NON_NULL)
public class Server{
    private Long id;
    private String name;
    private String host;
    private String appKey;
    private String appSecret;
    private String accessToken;
    /**
    * accessToken
    */
    private Long expireTime;
    private Short typeId;

    public Long getId(){
            return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
            return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getHost(){
            return this.host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public String getAppKey(){
            return this.appKey;
    }

    public void setAppKey(String appKey){
        this.appKey = appKey;
    }

    public String getAppSecret(){
            return this.appSecret;
    }

    public void setAppSecret(String appSecret){
        this.appSecret = appSecret;
    }

    public String getAccessToken(){
            return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public Long getExpireTime(){
            return this.expireTime;
    }

    public void setExpireTime(Long expireTime){
        this.expireTime = expireTime;
    }

    public Short getTypeId(){
            return this.typeId;
    }

    public void setTypeId(Short typeId){
        this.typeId = typeId;
    }

    private String _query;

    public String get_query() {
        return _query;
    }

    public void set_query(String _query) {
        this._query = _query;
    }
}