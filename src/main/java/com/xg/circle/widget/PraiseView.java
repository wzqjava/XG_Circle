package com.xg.circle.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xg.circle.adapter.PraiseAdapter;
import com.xg.circle.widget.spannable.ISpanClick;


/**
 * 显示点赞
 * Created by Administrator on 2016/3/27 0027.
 */
public class PraiseView extends TextView{
    private ISpanClick mSpanClickListener;

    public void setSpanClickListener(ISpanClick listener){
        mSpanClickListener = listener;
    }
    public ISpanClick getSpanClickListener(){
        return  mSpanClickListener;
    }

    public PraiseView(Context context) {
        super(context);
    }

    public PraiseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PraiseView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(PraiseAdapter adapter){
        adapter.bindListView(this);
    }

}
