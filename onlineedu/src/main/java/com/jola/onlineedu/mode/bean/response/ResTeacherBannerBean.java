package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/11/18.
 */

public class ResTeacherBannerBean {


    /**
     * count : 1
     * next :
     * previous :
     * results : [{"id":1,"teacher_id":1,"summary":"新教师","teaching_courses":"语文 数学 英语","school":"","name":"李了了","cover_url":"http://yunketang.dev.attackt.com/media/cover_1539338871.jpg"}]
     */

    private int count;
    private String next;
    private String previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
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
         * teacher_id : 1
         * summary : 新教师
         * teaching_courses : 语文 数学 英语
         * school :
         * name : 李了了
         * cover_url : http://yunketang.dev.attackt.com/media/cover_1539338871.jpg
         */

        private int id;
        private int teacher_id;
        private String summary;
        private String teaching_courses;
        private String school;
        private String name;
        private String cover_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(int teacher_id) {
            this.teacher_id = teacher_id;
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

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

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
    }
}
