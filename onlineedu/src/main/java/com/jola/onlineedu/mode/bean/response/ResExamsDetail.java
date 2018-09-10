package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResExamsDetail {

    /**
     * data : {"exam":{"test_time":"2018-09-27T22:14:11","name":"九月考试","exam_score":234,"deadline":"2018-09-27T22:14:11","questions":[{"small_question_title":"你来自哪里？","small_question_content":"","select_h":"hh","big_question_serial":"1","select_e":"","select_d":"dd","select_g":"gg","select_f":"ff","select_a":"aa","select_c":"cc","select_b":"bb","answer":"","small_question_serial":"1","score":1,"answer_analysis":"","question_type":"选择题","big_question_title":"基础题","big_question_content":"基础题内容"}],"exam_type":1}}
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
         * exam : {"test_time":"2018-09-27T22:14:11","name":"九月考试","exam_score":234,"deadline":"2018-09-27T22:14:11","questions":[{"small_question_title":"你来自哪里？","small_question_content":"","select_h":"hh","big_question_serial":"1","select_e":"","select_d":"dd","select_g":"gg","select_f":"ff","select_a":"aa","select_c":"cc","select_b":"bb","answer":"","small_question_serial":"1","score":1,"answer_analysis":"","question_type":"选择题","big_question_title":"基础题","big_question_content":"基础题内容"}],"exam_type":1}
         */

        private ExamBean exam;

        public ExamBean getExam() {
            return exam;
        }

        public void setExam(ExamBean exam) {
            this.exam = exam;
        }

        public static class ExamBean {
            /**
             * test_time : 2018-09-27T22:14:11
             * name : 九月考试
             * exam_score : 234
             * deadline : 2018-09-27T22:14:11
             * questions : [{"small_question_title":"你来自哪里？","small_question_content":"","select_h":"hh","big_question_serial":"1","select_e":"","select_d":"dd","select_g":"gg","select_f":"ff","select_a":"aa","select_c":"cc","select_b":"bb","answer":"","small_question_serial":"1","score":1,"answer_analysis":"","question_type":"选择题","big_question_title":"基础题","big_question_content":"基础题内容"}]
             * exam_type : 1
             */

            private String test_time;
            private String name;
            private int exam_score;
            private String deadline;
            private int exam_type;
            private List<QuestionsBean> questions;

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

            public int getExam_type() {
                return exam_type;
            }

            public void setExam_type(int exam_type) {
                this.exam_type = exam_type;
            }

            public List<QuestionsBean> getQuestions() {
                return questions;
            }

            public void setQuestions(List<QuestionsBean> questions) {
                this.questions = questions;
            }

            public static class QuestionsBean {
                /**
                 * small_question_title : 你来自哪里？
                 * small_question_content :
                 * select_h : hh
                 * big_question_serial : 1
                 * select_e :
                 * select_d : dd
                 * select_g : gg
                 * select_f : ff
                 * select_a : aa
                 * select_c : cc
                 * select_b : bb
                 * answer :
                 * small_question_serial : 1
                 * score : 1
                 * answer_analysis :
                 * question_type : 选择题
                 * big_question_title : 基础题
                 * big_question_content : 基础题内容
                 */

                private String small_question_title;
                private String small_question_content;
                private String select_h;
                private String big_question_serial;
                private String select_e;
                private String select_d;
                private String select_g;
                private String select_f;
                private String select_a;
                private String select_c;
                private String select_b;
                private String answer;
                private String small_question_serial;
                private int score;
                private String answer_analysis;
                private String question_type;
                private String big_question_title;
                private String big_question_content;

                public String getSmall_question_title() {
                    return small_question_title;
                }

                public void setSmall_question_title(String small_question_title) {
                    this.small_question_title = small_question_title;
                }

                public String getSmall_question_content() {
                    return small_question_content;
                }

                public void setSmall_question_content(String small_question_content) {
                    this.small_question_content = small_question_content;
                }

                public String getSelect_h() {
                    return select_h;
                }

                public void setSelect_h(String select_h) {
                    this.select_h = select_h;
                }

                public String getBig_question_serial() {
                    return big_question_serial;
                }

                public void setBig_question_serial(String big_question_serial) {
                    this.big_question_serial = big_question_serial;
                }

                public String getSelect_e() {
                    return select_e;
                }

                public void setSelect_e(String select_e) {
                    this.select_e = select_e;
                }

                public String getSelect_d() {
                    return select_d;
                }

                public void setSelect_d(String select_d) {
                    this.select_d = select_d;
                }

                public String getSelect_g() {
                    return select_g;
                }

                public void setSelect_g(String select_g) {
                    this.select_g = select_g;
                }

                public String getSelect_f() {
                    return select_f;
                }

                public void setSelect_f(String select_f) {
                    this.select_f = select_f;
                }

                public String getSelect_a() {
                    return select_a;
                }

                public void setSelect_a(String select_a) {
                    this.select_a = select_a;
                }

                public String getSelect_c() {
                    return select_c;
                }

                public void setSelect_c(String select_c) {
                    this.select_c = select_c;
                }

                public String getSelect_b() {
                    return select_b;
                }

                public void setSelect_b(String select_b) {
                    this.select_b = select_b;
                }

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }

                public String getSmall_question_serial() {
                    return small_question_serial;
                }

                public void setSmall_question_serial(String small_question_serial) {
                    this.small_question_serial = small_question_serial;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public String getAnswer_analysis() {
                    return answer_analysis;
                }

                public void setAnswer_analysis(String answer_analysis) {
                    this.answer_analysis = answer_analysis;
                }

                public String getQuestion_type() {
                    return question_type;
                }

                public void setQuestion_type(String question_type) {
                    this.question_type = question_type;
                }

                public String getBig_question_title() {
                    return big_question_title;
                }

                public void setBig_question_title(String big_question_title) {
                    this.big_question_title = big_question_title;
                }

                public String getBig_question_content() {
                    return big_question_content;
                }

                public void setBig_question_content(String big_question_content) {
                    this.big_question_content = big_question_content;
                }
            }
        }
    }
}
