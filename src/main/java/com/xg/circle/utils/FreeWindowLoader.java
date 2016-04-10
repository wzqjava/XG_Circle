package com.xg.circle.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by xugang on 2016/3/16.
 * Describe popupwindows 窗口
 */
@SuppressWarnings("all")
public class FreeWindowLoader {
    private View mView;
    private PopupWindow popupWindow;

    public FreeWindowLoader(View mView) {
        this.mView = mView;
    }

    /**
     * 初始化方法
     *
     * @param ctx
     * @param width
     * @param height
     * @return
     */
    public FreeWindowLoader init(Context ctx, int width, int height) {
        popupWindow = new PopupWindow(ctx);

        popupWindow.setContentView(mView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        popupWindow.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        popupWindow.setHeight(height);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);


        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        return this;
    }

    public FreeWindowLoader setAnimationStyle(@StyleRes int animationStyle) {
        popupWindow.setAnimationStyle(animationStyle);
        return  this;
    }

    /**
     * 在屏幕上下左右显示PopupWindow
     *
     * @param activity
     * @param gravity
     * @param x
     * @param y
     */
    public void showAtLocation(Activity activity, int gravity, int x, int y) {
        showAtLocation(activity.getWindow().getDecorView(), gravity, x, y);
    }

    /**
     * @param view
     * @param gravity
     * @param x
     * @param y
     * @ClassName FreeWindowLoader.java
     * @MethodName show
     * @Description 显示在指定位置
     * @author xugang
     */
    public void showAtLocation(View view, int gravity, int x, int y) {
        if (popupWindow == null) {
            throw new NullPointerException("PopupWindow is null,please call the init(Context context) before this.");
        }
        popupWindow.showAtLocation(view, gravity, x, y);
    }

    /**
     * @param anchor
     * @param xoff
     * @param yoff
     * @ClassName FreeWindowLoader.java
     * @MethodName showAsDropDown
     * @Description 在相对位置显示
     * @author xugang
     */
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (popupWindow == null) {
            throw new NullPointerException("PopupWindow is null,please call the init(Context context) before this.");
        }
        popupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAsDropDown(View anchor) {
        if (popupWindow == null) {
            throw new NullPointerException("PopupWindow is null,please call the init(Context context) before this.");
        }
        popupWindow.showAsDropDown(anchor);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (popupWindow == null) {
            throw new NullPointerException("PopupWindow is null,please call the init(Context context) before this.");
        }
        popupWindow.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }
}
