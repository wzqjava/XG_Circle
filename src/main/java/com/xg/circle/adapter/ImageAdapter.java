package com.xg.circle.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xg.circle.R;
import com.xg.circle.activity.AlbumActivity;
import com.xg.circle.bean.ImageItemBean;
import com.xg.circle.utils.ImageLoaderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugang on 2016/3/22.
 * Describe 相册Adapter
 */
public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ImageItemBean> mList;
    private ImageLoadingListener loadingListener;
    private DisplayImageOptions options;
    public static final int MAXNUM = 9;

    public ArrayList<String> getListImage() {
        return mListImage;
    }

    private ArrayList<String> mListImage;
    private Context mContext;

    public ImageAdapter(Context ctx, List<ImageItemBean> mList, List<String> mListImage) {
        mInflater = LayoutInflater.from(ctx);
        this.mList = mList;
        this.mContext = ctx;
        options = ImageLoaderFactory.getImageGvOptions();
        this.mListImage = new ArrayList<>();
        if (mListImage != null)
            this.mListImage.addAll(mListImage);
        loadingListener = new ImageLoaderFactory.AnimateFirstDisplayListener();
    }

    public void setList(List<ImageItemBean> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (null == view) {
            view = mInflater.inflate(R.layout.gv_item_album, parent, false);
            view.setTag(new ViewHolder());
        }
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.init(position,view);
        return view;
    }

    private class ViewHolder implements View.OnClickListener{
        private String image;
        private ImageView imgAlbum;
        private CheckBox cbAlbumSelect;

        ImageItemBean imageItemBean;

        public void init(int position,View view) {
            imgAlbum = (ImageView)view.findViewById(R.id.imgAlbum);
            cbAlbumSelect = (CheckBox) view.findViewById(R.id.cbAlbumSelect);

            imageItemBean = mList.get(position);
            image = imageItemBean.imagePath;
            if (TextUtils.isEmpty(image) || "null".equals(image)) {
                image = imageItemBean.thumbnailPath;
            }
            if (mListImage.contains(image)) {
                cbAlbumSelect.setVisibility(View.VISIBLE);
            } else {
                cbAlbumSelect.setVisibility(View.GONE);
            }
            String path = ImageLoaderFactory.file + image;
            ImageLoader.getInstance().displayImage(path, imgAlbum, options, loadingListener);

            imgAlbum.setOnClickListener(this);
        }

        public void onClick(View v) {
            if (mListImage.contains(image)) {
                mListImage.remove(image);
                cbAlbumSelect.setVisibility(View.GONE);
                handler.sendEmptyMessage(0);
            } else {
                if (mListImage.size() >= MAXNUM) {
                    Toast.makeText(mContext, "图片达到上线", Toast.LENGTH_SHORT).show();
                    return;
                }
                mListImage.add(image);
                cbAlbumSelect.setVisibility(View.VISIBLE);
                handler.sendEmptyMessage(0);
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mContext instanceof AlbumActivity) {
                ((AlbumActivity) mContext).setTvRightFunction(mListImage);
            }
        }
    };
}