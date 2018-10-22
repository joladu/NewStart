package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by lenovo on 2018/9/10.
 */

public class ResForumListByTypeBean {


    /**
     * data : {"posts":[{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"法国刚给他","is_new":0,"is_hot":1,"created":"2018-10-10T23:22:07.531","id":13,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"fhjjhh","is_new":0,"is_hot":1,"created":"2018-10-08T22:44:51.100","id":12,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"wakana ","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396772121660.jpg","name":""},"is_top":1,"title":"还回家","is_new":0,"is_hot":1,"created":"2018-09-26T15:21:42.873","id":8,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"wakana ","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396772121660.jpg","name":""},"is_top":1,"title":"还回家","is_new":0,"is_hot":1,"created":"2018-09-26T15:21:42.540","id":7,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"wakana ","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396772121660.jpg","name":""},"is_top":1,"title":"111","is_new":0,"is_hot":1,"created":"2018-09-19T10:12:53.597","id":6,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"返回很反感九号公馆","is_new":0,"is_hot":1,"created":"2018-09-17T22:59:12.073","id":4,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"chvxfugff","is_new":0,"is_hot":1,"created":"2018-09-16T17:02:47.152","id":2,"is_essence":1}],"page":1,"pageSize":10}
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
         * posts : [{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"法国刚给他","is_new":0,"is_hot":1,"created":"2018-10-10T23:22:07.531","id":13,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"fhjjhh","is_new":0,"is_hot":1,"created":"2018-10-08T22:44:51.100","id":12,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"wakana ","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396772121660.jpg","name":""},"is_top":1,"title":"还回家","is_new":0,"is_hot":1,"created":"2018-09-26T15:21:42.873","id":8,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"wakana ","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396772121660.jpg","name":""},"is_top":1,"title":"还回家","is_new":0,"is_hot":1,"created":"2018-09-26T15:21:42.540","id":7,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"wakana ","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396772121660.jpg","name":""},"is_top":1,"title":"111","is_new":0,"is_hot":1,"created":"2018-09-19T10:12:53.597","id":6,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"返回很反感九号公馆","is_new":0,"is_hot":1,"created":"2018-09-17T22:59:12.073","id":4,"is_essence":1},{"comment_count":0,"post_type":{"id":1,"name":"灌水"},"user":{"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""},"is_top":1,"title":"chvxfugff","is_new":0,"is_hot":1,"created":"2018-09-16T17:02:47.152","id":2,"is_essence":1}]
         * page : 1
         * pageSize : 10
         */

        private int page;
        private int pageSize;
        private List<PostsBean> posts;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PostsBean {
            /**
             * comment_count : 0
             * post_type : {"id":1,"name":"灌水"}
             * user : {"username":"duzi","avatar_url":"http://yunketang.dev.attackt.com/media/avatar/15396902624908.jpg","name":""}
             * is_top : 1
             * title : 法国刚给他
             * is_new : 0
             * is_hot : 1
             * created : 2018-10-10T23:22:07.531
             * id : 13
             * is_essence : 1
             */

            private int comment_count;
            private PostTypeBean post_type;
            private UserBean user;
            private int is_top;
            private String title;
            private int is_new;
            private int is_hot;
            private String created;
            private int id;
            private int is_essence;

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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public int getIs_top() {
                return is_top;
            }

            public void setIs_top(int is_top) {
                this.is_top = is_top;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
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

            public int getIs_essence() {
                return is_essence;
            }

            public void setIs_essence(int is_essence) {
                this.is_essence = is_essence;
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
}
