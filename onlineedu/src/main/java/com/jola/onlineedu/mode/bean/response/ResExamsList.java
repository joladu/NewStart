package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResExamsList {

    /**
     * data : {"exams":[{"test_time":"2018-09-27T22:14:11","name":"九月考试","exam_score":234,"deadline":"2018-09-27T22:14:11","id":1}]}
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
        private List<ExamsBean> exams;

        public List<ExamsBean> getExams() {
            return exams;
        }

        public void setExams(List<ExamsBean> exams) {
            this.exams = exams;
        }

        public static class ExamsBean {
            /**
             * test_time : 2018-09-27T22:14:11
             * name : 九月考试
             * exam_score : 234
             * deadline : 2018-09-27T22:14:11
             * id : 1
             */

            private String test_time;
            private String name;
            private int exam_score;
            private String deadline;
            private int id;

            public String getTest_time() {
                return test_time;
            }

            public void setTest_time(String test_time) {
                this.test_time = test_time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getExam_score() {
                return exam_score;
            }

            public void setExam_score(int exam_score) {
                this.exam_score = exam_score;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
