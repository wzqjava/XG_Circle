package com.xg.circle.bean;

import java.io.Serializable;

/**
 * 病例圈留言实体
 * Created by xugang on 2016/3/27 0027.
 */
public class CommentItemBean  implements Serializable {
    private String id;
    private UserBean user;
    private UserBean toReplyUser;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public UserBean getToReplyUser() {
        return toReplyUser;
    }

    public void setToReplyUser(UserBean toReplyUser) {
        this.toReplyUser = toReplyUser;
    }

    @Override
    public String toString() {
        return "CommentItemBean{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", toReplyUser=" + toReplyUser +
                ", content='" + content + '\'' +
                '}';
    }
}
