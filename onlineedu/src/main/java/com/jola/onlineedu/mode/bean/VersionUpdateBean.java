package com.jola.onlineedu.mode.bean;

/**
 * Created by jola on 2018/12/4.
 */

public class VersionUpdateBean {


    /**
     * data : {"version":{"content":"1. aaa\r\n2. bbb","os":"Android","version_num":1,"version_no":"v1.0.1","down_url":"http://yunketang.dev.attackt.com/download/xxx.apk","release_time":"2018.12.04","update_version":"是","commit_time":"2018.12.04"}}
     * error_code : 0
     * error_msg : 查询成功
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
         * version : {"content":"1. aaa\r\n2. bbb","os":"Android","version_num":1,"version_no":"v1.0.1","down_url":"http://yunketang.dev.attackt.com/download/xxx.apk","release_time":"2018.12.04","update_version":"是","commit_time":"2018.12.04"}
         */

        private VersionBean version;

        public VersionBean getVersion() {
            return version;
        }

        public void setVersion(VersionBean version) {
            this.version = version;
        }

        public static class VersionBean {
            /**
             * content : 1. aaa
             2. bbb
             * os : Android
             * version_num : 1
             * version_no : v1.0.1
             * down_url : http://yunketang.dev.attackt.com/download/xxx.apk
             * release_time : 2018.12.04
             * update_version : 是
             * commit_time : 2018.12.04
             */

            private String content;
            private String os;
            private int version_num;
            private String version_no;
            private String down_url;
            private String release_time;
            private String update_version;
            private String commit_time;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getOs() {
                return os;
            }

            public void setOs(String os) {
                this.os = os;
            }

            public int getVersion_num() {
                return version_num;
            }

            public void setVersion_num(int version_num) {
                this.version_num = version_num;
            }

            public String getVersion_no() {
                return version_no;
            }

            public void setVersion_no(String version_no) {
                this.version_no = version_no;
            }

            public String getDown_url() {
                return down_url;
            }

            public void setDown_url(String down_url) {
                this.down_url = down_url;
            }

            public String getRelease_time() {
                return release_time;
            }

            public void setRelease_time(String release_time) {
                this.release_time = release_time;
            }

            public String getUpdate_version() {
                return update_version;
            }

            public void setUpdate_version(String update_version) {
                this.update_version = update_version;
            }

            public String getCommit_time() {
                return commit_time;
            }

            public void setCommit_time(String commit_time) {
                this.commit_time = commit_time;
            }
        }
    }
}
