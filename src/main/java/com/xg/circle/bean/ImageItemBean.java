package com.xg.circle.bean;

import java.io.Serializable;

/**
 * Created by xugang on 2016/3/22.
 * Describe  一个图像对象
 */
@SuppressWarnings("all")
public class ImageItemBean implements Serializable {
    public String imageId;
    public String thumbnailPath;
    public String imagePath;
    public boolean isSelected = false;

    @Override
    public String toString() {
        return "ImageItemBean{" +
                "imageId='" + imageId + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
