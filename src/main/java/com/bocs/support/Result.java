package com.bocs.support;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Description:<p> Http请求返回数据</p>
 * Created by songqi on 2017/4/9.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 请求是否被正确地处理。
     * 正确处理，返回true,发生异常，返回false
     */
    private boolean success;

    /**
     * 返回码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
