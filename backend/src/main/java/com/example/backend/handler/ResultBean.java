package com.example.backend.handler;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;


public record ResultBean<T>(Integer code, String message, T data) {
    public static <T> ResultBean<T> success(T data){
        return new ResultBean<>(HttpMessage.SUCCESS.getCode(), HttpMessage.SUCCESS.getMessage(), data);
    }

    public static <T> ResultBean<T> error(HttpMessage httpMessage){
        return new ResultBean<>(httpMessage.getCode(), httpMessage.getMessage(), null);
    }

    public static <T> ResultBean<T> error(Integer code, String message){
        return new ResultBean<>(code, message, null);
    }

    public String toJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
