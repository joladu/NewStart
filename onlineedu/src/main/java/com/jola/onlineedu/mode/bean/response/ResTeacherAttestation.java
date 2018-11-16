package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2018/11/13.
 */

public class ResTeacherAttestation {

    /**
     * data : {"teacher_certification":"http://yunketang.dev.attackt.com/media/15421037276157.png","id_card_behind_pic":"http://yunketang.dev.attackt.com/media/15421037275993.jpg","id_card_front_pic":"http://yunketang.dev.attackt.com/media/15421037278354.png"}
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
         * teacher_certification : http://yunketang.dev.attackt.com/media/15421037276157.png
         * id_card_behind_pic : http://yunketang.dev.attackt.com/media/15421037275993.jpg
         * id_card_front_pic : http://yunketang.dev.attackt.com/media/15421037278354.png
         */

        private String teacher_certification;
        private String id_card_behind_pic;
        private String id_card_front_pic;

        public String getTeacher_certification() {
            return teacher_certification;
        }

        public void setTeacher_certification(String teacher_certification) {
            this.teacher_certification = teacher_certification;
        }

        public String getId_card_behind_pic() {
            return id_card_behind_pic;
        }

        public void setId_card_behind_pic(String id_card_behind_pic) {
            this.id_card_behind_pic = id_card_behind_pic;
        }

        public String getId_card_front_pic() {
            return id_card_front_pic;
        }

        public void setId_card_front_pic(String id_card_front_pic) {
            this.id_card_front_pic = id_card_front_pic;
        }
    }

    @Override
    public String toString() {
        return "ResTeacherAttestation{" +
                "data=" + data +
                ", error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
