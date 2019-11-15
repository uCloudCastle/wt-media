package com.wt.media.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * Created by winston on 2019-11-13.
 */
 @JsonInclude(JsonInclude.Include.NON_NULL)
public class Camera{
    private Long id;
    private String code;
    private String name;
    private Long serverId;
    private String serverName;
    private Short serverTypeId;
    private String serverTypeName;

    public Long getId(){
            return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCode(){
            return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
            return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getServerId(){
            return this.serverId;
    }

    public void setServerId(Long serverId){
        this.serverId = serverId;
    }

    public Short getServerTypeId() {
        return serverTypeId;
    }

    public void setServerTypeId(Short serverTypeId) {
        this.serverTypeId = serverTypeId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerTypeName() {
        return serverTypeName;
    }

    public void setServerTypeName(String serverTypeName) {
        this.serverTypeName = serverTypeName;
    }

    private String _query;

    public String get_query() {
        return _query;
    }

    public void set_query(String _query) {
        this._query = _query;
    }
}
