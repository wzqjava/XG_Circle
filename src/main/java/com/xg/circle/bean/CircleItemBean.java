package com.xg.circle.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 朋友圈tem实体类
 * Created by Administrator on 2016/3/27 0027.
 */
public class CircleItemBean implements Serializable {
    public String id;
    public String content; //发表内容
    public String createTime;//发表日期
    public ArrayList<String> photos; //图片集合
    public List<UserBean> praise; //点赞列表
    public List<CommentItemBean> comments;//回复集合
    public UserBean user; //用户信息

    @Override
    public String toString() {
        return "CircleItemBean{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", photos=" + photos +
                ", praise=" + praise +
                ", comments=" + comments +
                ", user=" + user +
                '}';
    }

    public boolean isComment() {
        if (comments != null && comments.size() != 0)
            return true;
        return false;
    }

    public boolean isPraise() {
        if (praise != null && praise.size() != 0)
            return true;
        return false;
    }

}
