package com.jola.onlineedu.mode.bean.response;

import java.util.List;

/**
 * Created by jola on 2018/11/21.
 */

public class ResGroupChatBean {

    /**
     * data : {"chats":[{"username":"test","name":"张三","created":"2018-11-21T09:38:17","content":" 发送聊天内容","avatar_url":"http://127.0.0.1:8002/media/avatar/11","is_my_chat":1,"id":1}],"page":1,"pageSize":10,"pageCount":1}
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
         * chats : [{"username":"test","name":"张三","created":"2018-11-21T09:38:17","content":" 发送聊天内容","avatar_url":"http://127.0.0.1:8002/media/avatar/11","is_my_chat":1,"id":1}]
         * page : 1
         * pageSize : 10
         * pageCount : 1
         */

        private int page;
        private int pageSize;
        private int pageCount;
        private List<ChatsBean> chats;

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

        public List<ChatsBean> getChats() {
            return chats;
        }

        public void setChats(List<ChatsBean> chats) {
            this.chats = chats;
        }

        public static class ChatsBean {
            /**
             * username : test
             * name : 张三
             * created : 2018-11-21T09:38:17
             * content :  发送聊天内容
             * avatar_url : http://127.0.0.1:8002/media/avatar/11
             * is_my_chat : 1
             * id : 1
             */

            private String username;
            private String name;
            private String created;
            private String content;
            private String avatar_url;
            private int is_my_chat;
            private int id;

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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public int getIs_my_chat() {
                return is_my_chat;
            }

            public void setIs_my_chat(int is_my_chat) {
                this.is_my_chat = is_my_chat;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
