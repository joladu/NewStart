package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/12/1.
 */

public class ResMajorListBean {

    /**
     * data : {"count":2,"majors":[{"id":1,"name":"语文"},{"id":2,"name":"数学"}],"page":1,"pageSize":10,"pageCount":1}
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
         * majors : [{"id":1,"name":"语文"},{"id":2,"name":"数学"}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int count;
        private int page;
        private int pageSize;
        private int pageCount;
        private List<MajorsBean> majors;

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

        public List<MajorsBean> getMajors() {
            return majors;
        }

        public void setMajors(List<MajorsBean> majors) {
            this.majors = majors;
        }

        public static class MajorsBean {
            /**
             * id : 1
             * name : 语文
             */

            private int id;
            private String name;

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