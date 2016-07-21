package com.johnny.service.impl;

import com.johnny.common.rs.RespObj;
import com.johnny.common.rs.RespType;
import com.johnny.common.utils.JSONUtil;

/**
 * Created by johnny01.yang on 2016/7/21.
 */
public class BaseServiceImpl {


    protected String successJson(Object message){
        RespObj respObj = new RespObj();
        respObj.setCode(RespType.SUCCESS.getCode());
        respObj.setType(RespType.SUCCESS.getType());
        respObj.setMessage(message);
        return JSONUtil.toJson(respObj);
    }

    protected String exceptionJson(Exception e){
        RespObj respObj = new RespObj();
        respObj.setCode(RespType.PARAMETEREXCEPTION.getCode());
        respObj.setType(RespType.PARAMETEREXCEPTION.getType());
        respObj.setMessage(e.getMessage());
        return JSONUtil.toJson(respObj);
    }

}
