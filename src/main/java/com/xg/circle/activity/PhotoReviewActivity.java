package com.xg.circle.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;

import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xg.circle.R;
import com.xg.circle.utils.ImageLoaderFactory;

import java.util.Arrays;
import java.util.List;

import uk.co.senab.photoview.PhotoView;


/**
 * 照片查看器
 */
public class PhotoReviewActivity extends BaseAppCompatActivity implements ViewPager.OnPageChangeListener {

    private List<String> mList;

    private ViewPager viewPager;
    private TextView tvPageNum;

    public static String ARG = "arg";
    public static String LIST = "list";
    public static String ARRAY = "array";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_review);
        viewPager = (ViewPager)findViewById(R.id.pager);
        tvPageNum = (TextView)findViewById(R.id.tvPageNum);

        Intent intent = getIntent();
        int position = intent.getIntExtra(ARG, 0);
        mList = intent.getStringArrayListExtra(LIST);
        String[] array = intent.getStringArrayExtra(ARRAY);
        if (array != null) {
            mList = Arrays.asList(array);
        }
        viewPager.setAdapter(new ImageAdapter(this));
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(this);

        tvPageNum.setText(mList == null && mList.size() == 0 ? "0" : ((position + 1) + "/" + mList.size()));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvPageNum.setText((position + 1) + "/" + mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ImageAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private DisplayImageOptions options;

        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            options = ImageLoaderFactory.getImageVpOptions();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.layout_image, view, false);
            assert imageLayout != null;
            PhotoView imageView = (PhotoView) imageLayout.findViewById(R.id.image);
            final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);

            ImageLoader.getInstance().displayImage(mList.get(position), imageView, options, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    spinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    String message = null;
                    switch (failReason.getType()) {
                        case IO_ERROR:
                            message = "Input/Output error";
                            break;
                        case DECODING_ERROR:
                            message = "Image can't be decoded";
                            break;
                        case NETWORK_DENIED:
                            message = "Downloads are denied";
                            break;
                        case OUT_OF_MEMORY:
                            message = "Out Of Memory error";
                            break;
                        case UNKNOWN:
                            message = "Unknown error";
                            break;
                    }
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

                    spinner.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    spinner.setVisibility(View.GONE);
                }
            });

            view.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }
}
