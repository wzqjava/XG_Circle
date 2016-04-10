package com.xg.circle.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.xg.circle.adapter.CommentAdapter;

/**
 * 评论列表
 * Created by Administrator on 2016/3/27 0027.
 */
public class CommentLayout  extends LinearLayout {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommentLayout(Context context) {
        super(context);
    }

    public CommentLayout(Context context, AttributeSet attrs){
        super(context, attrs);

    }

    public CommentLayout(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }


    public void setAdapter(CommentAdapter adapter){
        adapter.bindListView(this);
    }

    public void setOnItemClick(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClick(OnItemLongClickListener listener){
        mOnItemLongClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return mOnItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener(){
        return mOnItemLongClickListener;
    }


    public  interface OnItemClickListener{
        public void onItemClick(int position);
    }

    public  interface OnItemLongClickListener{
        public void onItemLongClick(int position);
    }
}