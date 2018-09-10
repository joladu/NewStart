package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResTeacherList {

    /**
     * count : 1
     * next : null
     * previous : null
     * results : [{"id":1,"username":"13407539951","name":"李薇薇","avatar":"","sex":"2","teacher_certification_id":"","teacher_certification":"","id_card_front_pic":"","id_card_behind_pic":"","summary":"","teaching_courses":"","hot":100,"score":100}]
     */

    private int count;
    private Object next;
    private Object previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 1
         * username : 13407539951
         * name : 李薇薇
         * avatar :
         * sex : 2
         * teacher_certification_id :
         * teacher_certification :
         * id_card_front_pic :
         * id_card_behind_pic :
         * summary :
         * teaching_courses :
         * hot : 100
         * score : 100
         */

        private int id;
        private String username;
        private String name;
        private String avatar;
        private String sex;
        private String teacher_certification_id;
        private String teacher_certification;
        private String id_card_front_pic;
        private String id_card_behind_pic;
        private String summary;
        private String teaching_courses;
        private int hot;
        private int score;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTeacher_certification_id() {
            return teacher_certification_id;
        }

        public void setTeacher_certification_id(String teacher_certification_id) {
            this.teacher_certification_id = teacher_certification_id;
        }

        public String getTeacher_certification() {
            return teacher_certification;
        }

        public void setTeacher_certification(String teacher_certification) {
            this.teacher_certification = teacher_certification;
        }

        public String getId_card_front_pic() {
            return id_card_front_pic;
        }

        public void setId_card_front_pic(String id_card_front_pic) {
            this.id_card_front_pic = id_card_front_pic;
        }

        public String getId_card_behind_pic() {
            return id_card_behind_pic;
        }

        public void setId_card_behind_pic(String id_card_behind_pic) {
            this.id_card_behind_pic = id_card_behind_pic;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTeaching_courses() {
            return teaching_courses;
        }

        public void setTeaching_courses(String teaching_courses) {
            this.teaching_courses = teaching_courses;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
