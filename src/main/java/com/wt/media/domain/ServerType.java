package com.wt.media.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * Created by winston on 2019-11-13.
 */
 @JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerType{
    private Short id;
    private String name;

    public Short getId(){
            return this.id;
    }

    public void setId(Short id){
        this.id = id;
    }

    public String getName(){
            return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    private String _query;

    public String get_query() {
        return _query;
    }

    public void set_query(String _query) {
        this._query = _query;
    }
}