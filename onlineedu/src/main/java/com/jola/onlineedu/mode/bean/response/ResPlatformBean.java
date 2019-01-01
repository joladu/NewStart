package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2019/1/1.
 */

public class ResPlatformBean {

    /**
     * data : {"count":2,"organs":[{"base_url":"http://yunketang.dev.attackt.com","id":1,"name":"主平台"},{"base_url":"http://yunketang.dev.attackt.com","id":2,"name":"郑州二中"}],"page":1,"pageSize":10,"pageCount":1}
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
         * count : 2
         * organs : [{"base_url":"http://yunketang.dev.attackt.com","id":1,"name":"主平台"},{"base_url":"http://yunketang.dev.attackt.com","id":2,"name":"郑州二中"}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int count;
        private int page;
        private int pageSize;
        private int pageCount;
        private List<OrgansBean> organs;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

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

        public List<OrgansBean> getOrgans() {
            return organs;
        }

        public void setOrgans(List<OrgansBean> organs) {
            this.organs = organs;
        }

        public static class OrgansBean {
            /**
             * base_url : http://yunketang.dev.attackt.com
             * id : 1
             * name : 主平台
             */

            private String base_url;
            private int id;
            private String name;

            public String getBase_url() {
                return base_url;
            }

            public void setBase_url(String base_url) {
                this.base_url = base_url;
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
}
