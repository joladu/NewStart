package com.jola.shengfan.toutiaojola.mode.response;

/**
 * Created by lenovo on 2018/10/10.
 */

public class ResultResponse<T> {
    public String hasMore;
    public String message;
    public String success;
    public T data;

    public ResultResponse(String hasMore, String message, String success, T data) {
        this.hasMore = hasMore;
        this.message = message;
        this.success = success;
        this.data = data;
    }
}
