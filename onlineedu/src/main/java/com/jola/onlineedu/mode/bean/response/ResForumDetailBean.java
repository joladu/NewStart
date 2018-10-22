package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResForumDetailBean {


    /**
     * data : {"post":{"content":"发过火家家户户","is_top":1,"is_essence":1,"title":"法国刚给他","images":[],"is_hot":1,"created":"2018-10-10T23:22:07.531","id":13,"is_new":0},"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""}}
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
         * post : {"content":"发过火家家户户","is_top":1,"is_essence":1,"title":"法国刚给他","images":[],"is_hot":1,"created":"2018-10-10T23:22:07.531","id":13,"is_new":0}
         * post_type : {"id":1,"name":"灌水"}
         * user : {"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""}
         */

        private PostBean post;
        private PostTypeBean post_type;
        private UserBean user;

        public PostBean getPost() {
            return post;
        }

        public void setPost(PostBean post) {
            this.post = post;
        }

        public PostTypeBean getPost_type() {
            return post_type;
        }

        public void setPost_type(PostTypeBean post_type) {
            this.post_type = post_type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class PostBean {
            /**
             * content : 发过火家家户户
             * is_top : 1
             * is_essence : 1
             * title : 法国刚给他
             * images : []
             * is_hot : 1
             * created : 2018-10-10T23:22:07.531
             * id : 13
             * is_new : 0
             */

            private String content;
            private int is_top;
            private int is_essence;
            private String title;
            private int is_hot;
            private String created;
            private int id;
            private int is_new;
            private List<String> images;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIs_top() {
                return is_top;
            }

            public void setIs_top(int is_top) {
                this.is_top = is_top;
            }

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

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class PostTypeBean {
            /**
             * id : 1
             * name : 灌水
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

        public static class UserBean {
            /**
             * username : duzi
             * avatar_url : http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg
             * name :
             */

            private String username;
            private String avatar_url;
            private String name;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
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
