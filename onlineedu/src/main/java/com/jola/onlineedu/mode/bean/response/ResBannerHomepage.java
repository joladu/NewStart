package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2018/9/24.
 */

public class ResBannerHomepage {

    /**
     * id : 1
     * name : 图片1
     * order_no : 1
     * img : img_1537443054.jpeg
     * advertising_url : http://xxxx
     * intro :
     * url_type : 0
     */

    private int id;
    private String name;
    private int order_no;
    private String img;
    private String advertising_url;
    private String intro;
    private int url_type;

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

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAdvertising_url() {
        return advertising_url;
    }

    public void setAdvertising_url(String advertising_url) {
        this.advertising_url = advertising_url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getUrl_type() {
        return url_type;
    }

    public void setUrl_type(int url_type) {
        this.url_type = url_type;
    }
}
