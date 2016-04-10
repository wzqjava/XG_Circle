package com.xg.circle.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xg.circle.R;
import com.xg.circle.adapter.AlbumAdapter;
import com.xg.circle.adapter.ImageAdapter;
import com.xg.circle.bean.ImageBucketBean;
import com.xg.circle.utils.AlbumTask;
import com.xg.circle.utils.FreeWindowLoader;
import com.xg.circle.view.AlbumView;

import java.util.List;

/**
 * 相册多选
 */
public class AlbumActivity extends BaseAppCompatActivity implements AlbumView {

    private GridView gv;
    private TextView tvAlbumFolder;
    private TextView tvPhotoNum;
    private RelativeLayout relAlbum;
    private ProgressBar progressBar;
    private TextView tvCheckPhoto;

    List<ImageBucketBean> dataList;

    private int position = 0;

    public final static String DATA = "data";

    private List<String> mlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        relAlbum = (RelativeLayout) findViewById(R.id.relAlbum);
        tvPhotoNum = (TextView) findViewById(R.id.tvPhotoNum);
        tvAlbumFolder = (TextView) findViewById(R.id.tvAlbumFolder);
        tvCheckPhoto = (TextView) findViewById(R.id.tvCheckPhoto);
        gv = (GridView) findViewById(R.id.gv);
        mlist = getIntent().getStringArrayListExtra(DATA);

        setTvRightFunction(mlist);

        declaringDangerousPermissions();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvCheckPhoto:
                Intent intent = new Intent();
                intent.putStringArrayListExtra(DATA, mAdapter == null ? null : mAdapter.getListImage());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tvAlbumFolder:
                try {
                    showAlbum();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void declaringDangerousPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
            } else {
                openAlbum();
            }
        } else {
            openAlbum();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // 请求权限请求被通过
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    // 请求权限请求被拒绝
                    Toast.makeText(this, "请求被拒绝", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void openAlbum() {
        AlbumTask task = new AlbumTask(this, this);
        task.execute();
    }

    @Override
    public void onDisplayProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void notifyDataSetChanged(List<ImageBucketBean> res) {
        if (res != null) {
            dataList = res;
            if (dataList != null && dataList.size() > 0) {
                openAlbum(dataList.get(position), position);
            }
        }
    }

    FreeWindowLoader loader;

    private void showAlbum() {
        View view = View.inflate(this, R.layout.layout_lv_normal, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.6);
        int[] location = new int[2];
        // 获得位置
        relAlbum.getLocationOnScreen(location);
        loader = new FreeWindowLoader(view);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new AlbumAdapter(this, dataList, position));
        loader.init(this, ViewGroup.LayoutParams.MATCH_PARENT, height).setAnimationStyle(R.style.popupAnimation).showAtLocation(relAlbum, Gravity.NO_GRAVITY, 0, location[1] - height);
    }

    private ImageAdapter mAdapter;

    public void openAlbum(@NonNull ImageBucketBean bucketBean, int position) {
        if (loader != null) {
            loader.dismiss();
        }
        this.position = position;
        tvAlbumFolder.setText(getString(R.string.albumFolder, bucketBean.bucketName));
        tvPhotoNum.setText(getString(R.string.photoNum, String.valueOf(bucketBean.count)));
        if (null == mAdapter) {
            mAdapter = new ImageAdapter(this, bucketBean.imageList, mlist);
            gv.setAdapter(mAdapter);
        } else {
            mAdapter.setList(bucketBean.imageList);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void setTvRightFunction(List<String> list) {
        if (list == null || list.size() == 0) {
            tvCheckPhoto.setText(getString(R.string.checkPhoto, 0,ImageAdapter.MAXNUM));

        } else {
            tvCheckPhoto.setText(getString(R.string.checkPhoto, list.size(), ImageAdapter.MAXNUM));
        }
    }
}
