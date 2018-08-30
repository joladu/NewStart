package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/8/30.
 */

public class ResponseSimpleResult {

    /**
     * error_code : 0
     * error_msg : success
     */

    private int error_code;
    private String error_msg;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
