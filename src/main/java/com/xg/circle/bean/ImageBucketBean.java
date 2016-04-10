package com.xg.circle.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xugang on 2016/3/22.
 * Describe 一个目录的相册对象
 */
@SuppressWarnings("all")
public class ImageBucketBean implements Serializable {
    public int count = 0;
    public String bucketName;
    public List<ImageItemBean> imageList;

    @Override
    public String toString() {
        return "ImageBucketBean{" +
                "count=" + count +
                ", bucketName='" + bucketName + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
