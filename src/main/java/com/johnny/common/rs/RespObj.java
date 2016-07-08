package com.johnny.common.rs;

import com.google.common.base.MoreObjects;

/**
 * define a common respObj
 *
 * Created by johnson.yang on 2016/5/11.
 */
public class RespObj {

    private String code;
    private String type;
    private Object message;

    public RespObj(){}

    public RespObj(String code, String type, Object message){
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String toString(){
        return MoreObjects.toStringHelper(RespObj.class)
                .add("code", code).add("type", type).add("message", message).toString();
    }
}
