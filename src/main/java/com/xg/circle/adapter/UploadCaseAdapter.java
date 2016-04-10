package com.xg.circle.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xg.circle.R;
import com.xg.circle.activity.AlbumActivity;
import com.xg.circle.activity.BaseAppCompatActivity;
import com.xg.circle.utils.ImageLoaderFactory;
import com.xg.circle.utils.K;

import java.util.ArrayList;

/**
 * 添加图片，上传朋友圈
 * Created by Administrator on 2016/3/25 0025.
 */
public class UploadCaseAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private BaseAppCompatActivity mContext;

    private ArrayList<String> mList;

    public UploadCaseAdapter(BaseAppCompatActivity mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return mList.size() == position ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = mInflater.inflate(R.layout.gv_item_image, parent, false);
            view.setTag(new ViewHolder());
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.init(position,view);
        return view;
    }

    class ViewHolder implements View.OnClickListener{
        ImageView imgUploadCases;
        int position;

        public void init(int position,View view) {
            this.position = position;
            imgUploadCases = (ImageView)view.findViewById(R.id.imgUploadCases);
            imgUploadCases.setOnClickListener(this);
            if ((getCount() - 1) == position) {
                imgUploadCases.setImageResource(R.drawable.select_image);
            } else {
                String uri = mList.get(position);
                ImageLoader.getInstance().displayImage(ImageLoaderFactory.file + uri, imgUploadCases);
            }
        }

        public void onClick(View view) {
            if (position >= getCount() - 1) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                intent.putStringArrayListExtra(AlbumActivity.DATA, mList);
                mContext.startActivityForResult(intent, K.ALBUMCUS);
            }
        }
    }
}
