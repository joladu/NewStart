package com.jola.newnews.mode.http.exception;

/**
 * Created by lenovo on 2018/7/23.
 */

public class ApiException extends Exception {
    private int code;
    public ApiException(String msg){
        super(msg);
    }
    public ApiException(String msg,int code){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
