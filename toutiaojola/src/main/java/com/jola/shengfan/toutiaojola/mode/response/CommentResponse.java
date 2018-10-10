package com.jola.shengfan.toutiaojola.mode.response;

import com.jola.shengfan.toutiaojola.mode.entity.CommentData;

import java.util.List;

/**
 * Created by lenovo on 2018/10/10.
 */

public class CommentResponse {
    public int total_number;
    public boolean has_more;
    public List<CommentData> data;
}
