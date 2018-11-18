package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/11/18.
 */

public class ResDownloadsBean {

    /**
     * data : {"downloads":[{"name":"资源1","cover_url":"http://127.0.0.1:8002/media/xx","created":"2018-11-17T11:28:01"}],"page":1,"pageSize":10,"pageCount":1}
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
         * downloads : [{"name":"资源1","cover_url":"http://127.0.0.1:8002/media/xx","created":"2018-11-17T11:28:01"}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int page;
        private int pageSize;
        private int pageCount;
        private List<DownloadsBean> downloads;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<DownloadsBean> getDownloads() {
            return downloads;
        }

        public void setDownloads(List<DownloadsBean> downloads) {
            this.downloads = downloads;
        }

        public static class DownloadsBean {
            /**
             * name : 资源1
             * cover_url : http://127.0.0.1:8002/media/xx
             * created : 2018-11-17T11:28:01
             */

            private String name;
            private String cover_url;
            private String created;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }
        }
    }
}
