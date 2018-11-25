package com.jola.onlineedu.mode.bean.response;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResTeacherCourseDetail {


    /**
     * id : 1
     * username : test
     * name : 张三
     * avatar : http://yunketang.dev.attackt.com/media/avatar/15393402485717.jpg
     * sex : 1
     * teacher_certification_id : x78dsa78d7a87
     * teacher_certification : http://yunketang.dev.attackt.com/media/cover_1539332559.jpg
     * id_card_front_pic : http://yunketang.dev.attackt.com/media/cover_1539332559.jpg
     * id_card_behind_pic : http://yunketang.dev.attackt.com/media/cover_1539332559.jpg
     * summary : 新教师
     * teaching_courses : 语文 数学 英语
     * hot : 0
     * score : 0
     */

    private int id;
    private String username;
    private String name;
    private String avatar;
    private int sex;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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
