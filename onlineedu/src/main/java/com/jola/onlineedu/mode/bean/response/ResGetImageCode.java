package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2018/9/9.
 */

public class ResGetImageCode {


    /**
     * data : {"captcha_img":"/captcha/image/20d8699afac91bb9bc4fc26f40f564eacbc91b6e/","captcha_key":"20d8699afac91bb9bc4fc26f40f564eacbc91b6e"}
     * error_code : 0
     * error_msg : success
     */

    private DataBean data;
    private int error_code;
    private String error_msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * captcha_img : /captcha/image/20d8699afac91bb9bc4fc26f40f564eacbc91b6e/
         * captcha_key : 20d8699afac91bb9bc4fc26f40f564eacbc91b6e
         */

        private String captcha_img;
        private String captcha_key;

        public String getCaptcha_img() {
            return captcha_img;
        }

        public void setCaptcha_img(String captcha_img) {
            this.captcha_img = captcha_img;
        }

        public String getCaptcha_key() {
            return captcha_key;
        }

        public void setCaptcha_key(String captcha_key) {
            this.captcha_key = captcha_key;
        }
    }
}
