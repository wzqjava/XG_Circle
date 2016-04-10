package com.xg.circle.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xg.circle.R;
import com.xg.circle.activity.AlbumActivity;
import com.xg.circle.bean.ImageBucketBean;
import com.xg.circle.utils.ImageLoaderFactory;

import java.util.List;

/**
 * Created by xugang on 2016/3/22.
 * Describe 相册Adapter
 */
public class AlbumAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private List<ImageBucketBean> mList;

    private Context mContext;

    private int mPosition = 0;

    public AlbumAdapter(Context ctx, List<ImageBucketBean> mList, int mPosition) {
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);
        this.mPosition = mPosition;
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
            view = mInflater.inflate(R.layout.lv_item_album, parent, false);
            view.setTag(new ViewHolder());
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.init(position, view);
        return view;
    }

    private class ViewHolder implements View.OnClickListener {
        private int position;
        private ImageView imgAlbumIcon;
        private TextView tvAlbumName;
        private TextView tvAlbumNum;
        private RadioButton rbAlbumItem;
        private RelativeLayout relAlbumItem;

        ImageBucketBean imageBucketBean;

        public void init(int position, View view) {
            this.position = position;
            imgAlbumIcon = (ImageView) view.findViewById(R.id.imgAlbumIcon);
            tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
            tvAlbumNum = (TextView) view.findViewById(R.id.tvAlbumNum);
            rbAlbumItem = (RadioButton) view.findViewById(R.id.rbAlbumItem);
            relAlbumItem = (RelativeLayout)view.findViewById(R.id.relAlbumItem);

            imageBucketBean = mList.get(position);
            tvAlbumName.setText(imageBucketBean.bucketName);
            tvAlbumNum.setText(String.valueOf(imageBucketBean.count));
            relAlbumItem.setOnClickListener(this);
            rbAlbumItem.setVisibility(mPosition == position ? View.VISIBLE : View.GONE);
            String image = null;
            if (imageBucketBean.imageList != null && imageBucketBean.imageList.size() > 0) {
                image = imageBucketBean.imageList.get(0).imagePath;
                if (TextUtils.isEmpty(image)) {
                    image = imageBucketBean.imageList.get(0).thumbnailPath;
                }
            }
            ImageLoader.getInstance().displayImage(ImageLoaderFactory.file + image, imgAlbumIcon);
        }

        public void onClick(View v) {
            if (mContext instanceof AlbumActivity) {
                ((AlbumActivity) mContext).openAlbum(imageBucketBean, position);
            }
        }
    }
}
