package com.xg.circle.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * 
* @ClassName: ActionItemBean
* @Description: 弹窗内部子类项（绘制标题和图标） 
* @author yiw
* @date 2015-12-28 下午3:43:30 
*
 */
public class ActionItemBean implements Serializable {
	// 定义图片对象
	public Drawable mDrawable;
	// 定义文本对象
	public CharSequence mTitle;

	public ActionItemBean(Drawable drawable, CharSequence title) {
		this.mDrawable = drawable;
		this.mTitle = title;
	}

	public ActionItemBean(CharSequence title) {
		this.mDrawable = null;
		this.mTitle = title;
	}

	public ActionItemBean(Context context, int titleId, int drawableId) {
		this.mTitle = context.getResources().getText(titleId);
		this.mDrawable = context.getResources().getDrawable(drawableId);
	}

	public ActionItemBean(Context context, CharSequence title, int drawableId) {
		this.mTitle = title;
		this.mDrawable = context.getResources().getDrawable(drawableId);
	}

	public void setItemTv(CharSequence tv) {
		mTitle = tv;
	}
}
