package com.xg.circle.widget.spannable;

import android.content.Context;
import android.text.SpannableString;
import android.widget.Toast;

/**
 * @author yiw
 * @ClassName: NameClickListener
 * @Description: 点赞和评论中人名的点击事件
 * @date 2015-01-02 下午3:42:21
 */
public class NameClickListener implements ISpanClick {
    private SpannableString userName;
    private String userId;
    private Context mContext;

    public NameClickListener(Context mContext, SpannableString name, String userid) {
        this.mContext = mContext;
        this.userName = name;
        this.userId = userid;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(mContext, userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
    }
}
