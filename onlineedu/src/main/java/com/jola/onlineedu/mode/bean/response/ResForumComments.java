package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/10/22.
 */

public class ResForumComments {

    /**
     * data : {"page":1,"pageSize":10,"pageCount":28,"comments":[{"created":"2018-10-12T15:16:13","has_praise":1,"content":"评论3 分威锋网发问","user":{"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/22","name":""},"praise_count":5,"id":6},{"created":"2018-10-12T15:16:07","has_praise":1,"content":"评论2","user":{"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/22","name":""},"praise_count":2,"id":5}]}
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
         * page : 1
         * pageSize : 10
         * pageCount : 28
         * comments : [{"created":"2018-10-12T15:16:13","has_praise":1,"content":"评论3 分威锋网发问","user":{"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/22","name":""},"praise_count":5,"id":6},{"created":"2018-10-12T15:16:07","has_praise":1,"content":"评论2","user":{"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/22","name":""},"praise_count":2,"id":5}]
         */

        private int page;
        private int pageSize;
        private int pageCount;
        private List<CommentsBean> comments;

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

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * created : 2018-10-12T15:16:13
             * has_praise : 1
             * content : 评论3 分威锋网发问
             * user : {"username":"khaki","avatar_url":"http://127.0.0.1:8002/media/avatar/22","name":""}
             * praise_count : 5
             * id : 6
             */

            private String created;
            private int has_praise;
            private String content;
            private UserBean user;
            private int praise_count;
            private int id;

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getHas_praise() {
                return has_praise;
            }

            public void setHas_praise(int has_praise) {
                this.has_praise = has_praise;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
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

            public static class UserBean {
                /**
                 * username : khaki
                 * avatar_url : http://127.0.0.1:8002/media/avatar/22
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
