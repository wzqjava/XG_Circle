package com.xg.circle.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.xg.circle.bean.ImageBucketBean;
import com.xg.circle.view.AlbumView;

import java.util.List;

/**
 * Created by xugang on 2016/3/23.
 * Describe 获取相册异步任务
 */
public class AlbumTask extends AsyncTask<Void, Void, List<ImageBucketBean>> {

    private AlbumView view;
    private Context ctx;

    public AlbumTask(Context ctx, AlbumView view) {
        this.view = view;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        view.onDisplayProgress();
    }

    @Override
    protected List<ImageBucketBean> doInBackground(Void... params) {
        AlbumHelper helper = AlbumHelper.getHelper();
        helper.init(ctx.getApplicationContext());

        return helper.getImagesBucketList(false);
    }

    @Override
    protected void onPostExecute(List<ImageBucketBean> res) {
        super.onPostExecute(res);
        view.onHideProgress();
        view.notifyDataSetChanged(res);
    }
}
