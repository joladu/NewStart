package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResForumListByTypeBean {

    /**
     * data : {"posts":[{"is_essence":1,"title":"读大学好不好","created":null,"comment_count":2,"post_type":{"id":1,"name":"提问"},"user":"admin","id":1}]}
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
        private List<PostsBean> posts;

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PostsBean {
            /**
             * is_essence : 1
             * title : 读大学好不好
             * created : null
             * comment_count : 2
             * post_type : {"id":1,"name":"提问"}
             * user : admin
             * id : 1
             */

            private int is_essence;
            private String title;
            private String created;
            private int comment_count;
            private PostTypeBean post_type;
            private String user;
            private int id;

            public int getIs_essence() {
                return is_essence;
            }

            public void setIs_essence(int is_essence) {
                this.is_essence = is_essence;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public PostTypeBean getPost_type() {
                return post_type;
            }

            public void setPost_type(PostTypeBean post_type) {
                this.post_type = post_type;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class PostTypeBean {
                /**
                 * id : 1
                 * name : 提问
                 */

                private int id;
                private String name;

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
            }
        }
    }
}
