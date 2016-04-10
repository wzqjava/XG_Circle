package com.xg.circle.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.xg.circle.R;
import com.xg.circle.activity.PhotoReviewActivity;
import com.xg.circle.bean.CircleItemBean;
import com.xg.circle.bean.CommentItemBean;
import com.xg.circle.bean.UserBean;
import com.xg.circle.utils.ImageLoaderFactory;
import com.xg.circle.widget.CirclePopupWindow;
import com.xg.circle.widget.CommentLayout;
import com.xg.circle.widget.MultiImageView;
import com.xg.circle.widget.PraiseView;
import com.xg.circle.widget.spannable.ISpanClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 朋友圈适配器
 * Created by Administrator on 2016/3/27 0027.
 */
public class CircleAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private DisplayImageOptions optionsCircle = ImageLoaderFactory.getImageLvOptions(new CircleBitmapDisplayer());
    private DisplayImageOptions options = ImageLoaderFactory.getImageGvOptions();

    private List<CircleItemBean> mList;

    public CircleAdapter(Context mContext, List<CircleItemBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
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
        ViewHolder holder;
        if (null == view) {
            view = mInflater.inflate(R.layout.lv_item_circle, parent, false);
            holder = new ViewHolder();
            holder.tvPraise = (PraiseView) view.findViewById(R.id.tvPraise);
            holder.tvTime = (TextView) view.findViewById(R.id.tvTime);
            holder.tvUserName = (TextView) view.findViewById(R.id.tvUserName);
            holder.tvUserContent = (TextView) view.findViewById(R.id.tvUserContent);
            holder.linComment = (CommentLayout) view.findViewById(R.id.linComment);
            holder.imgUserIcon = (ImageView) view.findViewById(R.id.imgUserIcon);
            holder.linPraise = (LinearLayout) view.findViewById(R.id.linPraise);
            holder.vLine = view.findViewById(R.id.viewLine);
            holder.imgComment = (ImageView) view.findViewById(R.id.imgComment);
            holder.multiImagView = (MultiImageView) view.findViewById(R.id.multiImagView);

            holder.commentAdapter = new CommentAdapter(mContext);
            holder.praiseAdapter = new PraiseAdapter(mContext);
            holder.popupWindow = new CirclePopupWindow(mContext);

            holder.tvPraise.setAdapter(holder.praiseAdapter);
            holder.linComment.setAdapter(holder.commentAdapter);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CircleItemBean bean = mList.get(position);
        ImageLoader.getInstance().displayImage(bean.user.getHeadUrl(), holder.imgUserIcon, optionsCircle);
        holder.tvUserName.setText(bean.user.getName());
        holder.tvUserContent.setText(bean.content);
        holder.tvTime.setText(bean.createTime);
        holder.imgComment.setOnClickListener(new MyClick(holder));

        //处理图片点击
        final ArrayList<String> photos = bean.photos;
        if (photos != null && photos.size() > 0) {
            holder.multiImagView.setVisibility(View.VISIBLE);
            holder.multiImagView.setList(photos);
            holder.multiImagView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    // 因为单张图片时，图片实际大小是自适应的，imageLoader缓存时是按测量尺寸缓存的
                    Intent intent = new Intent(mContext, PhotoReviewActivity.class);
                    intent.putExtra(PhotoReviewActivity.ARG, position);
                    intent.putStringArrayListExtra(PhotoReviewActivity.LIST, photos);
                    mContext.startActivity(intent);
                }
            });
        } else {
            holder.multiImagView.setVisibility(View.GONE);
        }

        //评论列表
        List<CommentItemBean> commentItemBeans = bean.comments;
        //点赞列表
        List<UserBean> userBeans = bean.praise;
        if (!bean.isComment() && !bean.isPraise()) {
            holder.linPraise.setVisibility(View.GONE);
        } else {
            holder.linPraise.setVisibility(View.VISIBLE);
            if (!bean.isPraise() || !bean.isComment()) {
                holder.vLine.setVisibility(View.GONE);
            } else {
                holder.vLine.setVisibility(View.VISIBLE);
            }
            if (bean.isComment()) {
                holder.linComment.setVisibility(View.VISIBLE);
                holder.linComment.setOnItemClick(new CommentLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
                holder.linComment.setOnItemLongClick(new CommentLayout.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
                holder.commentAdapter.setDatas(commentItemBeans);
                holder.commentAdapter.notifyDataSetChanged();
            } else {
                holder.linComment.setVisibility(View.GONE);
            }
            if (bean.isPraise()) {
                holder.tvPraise.setVisibility(View.VISIBLE);
                holder.tvPraise.setSpanClickListener(new ISpanClick() {
                    @Override
                    public void onClick(int position) {

                    }
                });
                holder.praiseAdapter.setDatas(userBeans);
                holder.praiseAdapter.notifyDataSetChanged();
            } else {
                holder.tvPraise.setVisibility(View.GONE);
            }
        }

        return view;
    }

    private class MyClick implements View.OnClickListener {

        private ViewHolder holder;

        public MyClick(ViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgComment:
                    holder.popupWindow.showPopupWindow(v);
                    break;
            }
        }
    }

    class ViewHolder {
        private ImageView imgUserIcon;
        private TextView tvUserName;
        private TextView tvUserContent;
        private TextView tvTime;
        private ImageView imgComment;

        //点赞和留言
        private LinearLayout linPraise;
        private PraiseView tvPraise;
        private CommentLayout linComment;
        private View vLine;
        private MultiImageView multiImagView;

        private CommentAdapter commentAdapter;
        private PraiseAdapter praiseAdapter;

        private CirclePopupWindow popupWindow = new CirclePopupWindow(mContext);

    }
}
