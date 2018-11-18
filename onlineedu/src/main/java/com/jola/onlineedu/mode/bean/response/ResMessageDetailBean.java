package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2018/11/17.
 */

public class ResMessageDetailBean {

    /**
     * data : {"username":"khaki","content":"私信内容","avatar_url":"http://127.0.0.1:8002/media/avatar/15393402485717.jpg","created":"2018-11-15T20:06:17","id":1,"name":"李强"}
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
         * username : khaki
         * content : 私信内容
         * avatar_url : http://127.0.0.1:8002/media/avatar/15393402485717.jpg
         * created : 2018-11-15T20:06:17
         * id : 1
         * name : 李强
         */

        private String username;
        private String content;
        private String avatar_url;
        private String created;
        private int id;
        private String name;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
