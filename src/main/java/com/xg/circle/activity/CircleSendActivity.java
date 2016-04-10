package com.xg.circle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.xg.circle.R;
import com.xg.circle.adapter.UploadCaseAdapter;
import com.xg.circle.utils.K;

import java.util.ArrayList;

/**
 * 发送病例圈
 */
public class CircleSendActivity extends BaseAppCompatActivity {

    private ArrayList<String> mList;

    private GridView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_send);
        gv = (GridView) findViewById(R.id.gv);
        mList = new ArrayList<>();
        gv.setAdapter(new UploadCaseAdapter(this, mList));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvCircleSend:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case K.ALBUMCUS:
                    if (data.getStringArrayListExtra(AlbumActivity.DATA) == null) return;
                    mList = data.getStringArrayListExtra(AlbumActivity.DATA);
                    if (mList != null)
                        for (String str : mList) {
                            Log.e("--------------", str);
                        }
                    gv.setAdapter(new UploadCaseAdapter(this, mList));
                    break;
            }
        }
    }
}
