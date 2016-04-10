package com.xg.circle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import com.xg.circle.R;
import com.xg.circle.bean.UserBean;
import com.xg.circle.widget.PraiseView;
import com.xg.circle.widget.spannable.CircleMovementMethod;
import com.xg.circle.widget.spannable.NameClickable;

import java.util.List;

/**
 * 点赞
 * Created by xugang on 2016/3/27 0027.
 */
public class PraiseAdapter {

    private PraiseView mListView;
    private List<UserBean> datas;
    private Context mContext;

    public PraiseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<UserBean> getDatas() {
        return datas;
    }

    public void setDatas(List<UserBean> datas) {
        this.datas = datas;
    }

    @NonNull
    public void bindListView(PraiseView listview) {
        if (listview == null) {
            throw new IllegalArgumentException("FavortListView is null ....");
        }
        mListView = listview;
    }


    public int getCount() {
        if (datas != null && datas.size() > 0) {
            return datas.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        if (datas != null && datas.size() > position) {
            return datas.get(position);
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public void notifyDataSetChanged() {
        if (mListView == null) {
            throw new NullPointerException("listview is null, please bindListView first...");
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (datas != null && datas.size() > 0) {
            //添加点赞图标
            builder.append(setImageSpan());
            //builder.append("  ");
            UserBean item = null;
            for (int i = 0; i < datas.size(); i++) {
                item = datas.get(i);
                if (item != null) {
                    builder.append(setClickableSpan(item.getName(), i));
                    if (i != datas.size() - 1) {
                        builder.append(", ");
                    }
                }
            }
        }
        mListView.setText(builder);
        mListView.setMovementMethod(new CircleMovementMethod(R.color.colorOrange));
    }

    @NonNull
    private SpannableString setClickableSpan(String textStr, int position) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new NameClickable(mContext, mListView.getSpanClickListener(), position), 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    private SpannableString setImageSpan() {
        String text = "  ";
        SpannableString imgSpanText = new SpannableString(text);
        imgSpanText.setSpan(new ImageSpan(mContext, R.drawable.icon_zan, DynamicDrawableSpan.ALIGN_BASELINE),
                0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return imgSpanText;
    }
}