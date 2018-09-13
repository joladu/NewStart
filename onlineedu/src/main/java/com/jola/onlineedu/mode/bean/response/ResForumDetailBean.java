package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResForumDetailBean {

    /**
     * data : {"post":{"images":[],"id":1,"comments":[{"content":"建瓯建瓯而归热狗机偶尔给人家偶尔巨佛问卷佛界无法","praise_count":34,"id":1,"user":"admin"},{"content":"快来评论呀，开学了，好开心。","praise_count":1,"id":2,"user":"test"}],"title":"读大学好不好"}}
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
         * post : {"images":[],"id":1,"comments":[{"content":"建瓯建瓯而归热狗机偶尔给人家偶尔巨佛问卷佛界无法","praise_count":34,"id":1,"user":"admin"},{"content":"快来评论呀，开学了，好开心。","praise_count":1,"id":2,"user":"test"}],"title":"读大学好不好"}
         */

        private PostBean post;

        public PostBean getPost() {
            return post;
        }

        public void setPost(PostBean post) {
            this.post = post;
        }

        public static class PostBean {
            /**
             * images : []
             * id : 1
             * comments : [{"content":"建瓯建瓯而归热狗机偶尔给人家偶尔巨佛问卷佛界无法","praise_count":34,"id":1,"user":"admin"},{"content":"快来评论呀，开学了，好开心。","praise_count":1,"id":2,"user":"test"}]
             * title : 读大学好不好
             */

            private int id;
            private String title;
            private List<String> images;
            private List<CommentsBean> comments;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<CommentsBean> getComments() {
                return comments;
            }

            public void setComments(List<CommentsBean> comments) {
                this.comments = comments;
            }

            public static class CommentsBean {
                /**
                 * content : 建瓯建瓯而归热狗机偶尔给人家偶尔巨佛问卷佛界无法
                 * praise_count : 34
                 * id : 1
                 * user : admin
                 */

                private String content;
                private int praise_count;
                private int id;
                private String user;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getPraise_count() {
                    return praise_count;
                }

                public void setPraise_count(int praise_count) {
                    this.praise_count = praise_count;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUser() {
                    return user;
                }

                public void setUser(String user) {
                    this.user = user;
                }
            }
        }
    }
}
