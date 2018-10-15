package com.jola.onlineedu.mode.bean.response;

/**
 * Created by jola on 2018/9/24.
 */

public class ResBannerHomepage {


    /**
     * id : 1
     * name : 产品1
     * order_no : 1
     * domain_img_url : http://yunketang.dev.attackt.com/media/img_1539315955.jpg
     * advertising_url : http://www.baidu.com
     * intro : 新产品
     */

    private int id;
    private String name;
    private int order_no;
    private String domain_img_url;
    private String advertising_url;
    private String intro;

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

    public String getDomain_img_url() {
        return domain_img_url;
    }

    public void setDomain_img_url(String domain_img_url) {
        this.domain_img_url = domain_img_url;
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
}
