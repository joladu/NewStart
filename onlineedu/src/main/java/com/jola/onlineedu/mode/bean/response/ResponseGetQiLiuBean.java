package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/8/30.
 */

public class ResponseGetQiLiuBean {

    /**
     * error_code : 0
     * error_msg : success
     * data : {"qiniu_domain":"http://onaxhr6pj.bkt.clouddn.com","token":"-3sfnsnfisnfi2342nsnisncisn","bucket_name":"airhire"}
     */

    private int error_code;
    private String error_msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * qiniu_domain : http://onaxhr6pj.bkt.clouddn.com
         * token : -3sfnsnfisnfi2342nsnisncisn
         * bucket_name : airhire
         */

        private String qiniu_domain;
        private String token;
        private String bucket_name;

        public String getQiniu_domain() {
            return qiniu_domain;
        }

        public void setQiniu_domain(String qiniu_domain) {
            this.qiniu_domain = qiniu_domain;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getBucket_name() {
            return bucket_name;
        }

        public void setBucket_name(String bucket_name) {
            this.bucket_name = bucket_name;
        }
    }
}
