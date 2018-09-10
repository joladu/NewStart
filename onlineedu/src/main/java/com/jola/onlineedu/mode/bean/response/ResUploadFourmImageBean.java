package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResUploadFourmImageBean {

    /**
     * data : {"img_url":"http://7xse50.com1.z0.glb.clouddn.com/jinji/yunketang/ykt_1536049054_7878.jpg"}
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
         * img_url : http://7xse50.com1.z0.glb.clouddn.com/jinji/yunketang/ykt_1536049054_7878.jpg
         */

        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
