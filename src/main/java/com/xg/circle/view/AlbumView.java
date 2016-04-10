package com.xg.circle.view;


import com.xg.circle.bean.ImageBucketBean;

import java.util.List;

/**
 * Created by xugang on 2016/4/11.
 * Describe
 */
public interface AlbumView {
    void onDisplayProgress();

    void onHideProgress();

    void notifyDataSetChanged(List<ImageBucketBean> res);
}
