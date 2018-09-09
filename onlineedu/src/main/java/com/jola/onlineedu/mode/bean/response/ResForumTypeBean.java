package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/9/9.
 */

public class ResForumTypeBean {

    /**
     * data : {"types":[{"id":1,"order_no":1,"name":"灌水"},{"id":2,"order_no":2,"name":"提问"},{"id":3,"order_no":3,"name":"考试"}]}
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
        private List<TypesBean> types;

        public List<TypesBean> getTypes() {
            return types;
        }

        public void setTypes(List<TypesBean> types) {
            this.types = types;
        }

        public static class TypesBean {
            /**
             * id : 1
             * order_no : 1
             * name : 灌水
             */

            private int id;
            private int order_no;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder_no() {
                return order_no;
            }

            public void setOrder_no(int order_no) {
                this.order_no = order_no;
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
