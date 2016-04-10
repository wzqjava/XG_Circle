package com.xg.circle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xg.circle.R;
import com.xg.circle.adapter.CircleAdapter;
import com.xg.circle.bean.CircleItemBean;
import com.xg.circle.bean.CommentItemBean;
import com.xg.circle.bean.UserBean;
import com.xg.circle.utils.ImageLoaderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleActivity extends BaseAppCompatActivity {

    private ImageView imgCheckPhoto;
    private ListView lv;

    private ImageView imgCircleUserIcon;
    private TextView tvCircleUserName;

    private List<CircleItemBean> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        imgCheckPhoto = (ImageView) findViewById(R.id.imgCheckPhoto);
        lv = (ListView) findViewById(R.id.lv);

        initData();

        init();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                finish();
                break;
            case R.id.imgCheckPhoto:
                startActivity(new Intent(this, CircleSendActivity.class));
                break;
        }
    }

    private void init() {
        View vHead = LayoutInflater.from(this).inflate(R.layout.layout_circle_top, null);
        imgCircleUserIcon = (ImageView) vHead.findViewById(R.id.imgCircleUserIcon);
        tvCircleUserName = (TextView) vHead.findViewById(R.id.tvcircleUserName);
        tvCircleUserName.setText("呵呵哒");
        ImageLoader.getInstance().displayImage("http://img4.duitang.com/uploads/item/201405/10/20140510180701_HaMGF.jpeg", imgCircleUserIcon, ImageLoaderFactory.getImageLvOptions(new RoundedBitmapDisplayer(10)));
        lv.addHeaderView(vHead);
        CircleAdapter adapter = new CircleAdapter(this, mList);
        lv.setAdapter(adapter);
    }

    private void initData() {
        String[] name = new String[]{"小米", "小明", "小李", "小组"};
        String[] content = new String[]{"哈哈哈哈哈哈哈好哈", "呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵", "洗XIIXII洗XII嘻嘻嘻嘻嘻嘻嘻", "啊啊啊啊啊啊啊啊啊啊啊啊啊", "噢噢噢噢哦哦哦哦哦哦", "方法反反复复反复反复反复反复反复反复反复反复反复", "人人人人人人人人人人人人人人人人人人人人人人人人"};
        String[] time = new String[]{"9月3日", "10月9日", "23分钟前", "刚刚", "11月24日"};
        String[] image = new String[]{"http://img10.3lian.com/sc6/show02/67/27/02.jpg",
                "http://img4.duitang.com/uploads/item/201408/30/20140830185433_FnJLA.jpeg",
                "http://tupian.enterdesk.com/2013/xll/012/26/3/1.jpg",
                "http://pic31.nipic.com/20130718/12834382_112335424179_2.jpg",
                "http://imga1.pic21.com/bizhi/140226/07916/s04.jpg",
                "http://a.hiphotos.baidu.com/zhidao/pic/item/18d8bc3eb13533fafae9926cabd3fd1f41345b10.jpg",
                "http://d.hiphotos.baidu.com/zhidao/pic/item/7c1ed21b0ef41bd5e6c559a057da81cb38db3dcb.jpg",
                "http://img05.tooopen.com/images/20140510/sy_60795391351.jpg",
                "http://e.hiphotos.baidu.com/zhidao/pic/item/78310a55b319ebc40bbc7c3e8326cffc1f171654.jpg",
                "http://imgstore.cdn.sogou.com/app/a/100540002/834169.jpg",
                "http://www.pp3.cn/uploads/allimg/111122/112U12H1-2.jpg",
                "http://pic39.nipic.com/20140321/9448607_213919680000_2.jpg",
                "http://pic31.nipic.com/20130726/8157563_104655714000_2.jpg",
                "http://img2.3lian.com/img2007/23/08/025.jpg"};
        int[] length = new int[]{0, 1, 2, 4, 5, 8, 9, 12};
        mList = new ArrayList<>();
        for (int k = 0; k < 15; k++) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < length[new Random().nextInt(length.length)]; i++) {
                list.add(image[new Random().nextInt(image.length)]);
            }
            List<UserBean> userBeanList = new ArrayList<>();
            for (int i = 0; i < length[new Random().nextInt(length.length)]; i++) {
                UserBean bean = new UserBean();
                bean.setName(name[new Random().nextInt(name.length)]);
                bean.setHeadUrl(image[new Random().nextInt(image.length)]);
                userBeanList.add(bean);
            }
            List<CommentItemBean> itemBeanList = new ArrayList<>();
            for (int i = 0; i < length[new Random().nextInt(length.length)]; i++) {
                CommentItemBean itemBean = new CommentItemBean();

                UserBean bean = new UserBean();
                bean.setName(name[new Random().nextInt(name.length)]);
                bean.setHeadUrl(image[new Random().nextInt(image.length)]);

                UserBean toReplyUser = new UserBean();
                toReplyUser.setName(name[new Random().nextInt(name.length)]);
                toReplyUser.setHeadUrl(image[new Random().nextInt(image.length)]);

                itemBean.setContent(content[new Random().nextInt(content.length)]);
                itemBean.setUser(bean);
                itemBean.setToReplyUser(toReplyUser);

                itemBeanList.add(itemBean);
            }


            CircleItemBean bean = new CircleItemBean();

            UserBean userBean = new UserBean();
            userBean.setName(name[new Random().nextInt(4)] + k);
            userBean.setHeadUrl(image[new Random().nextInt(14)]);
            bean.user = userBean;
            bean.content = content[new Random().nextInt(7)];
            bean.createTime = time[new Random().nextInt(5)];

            bean.photos = list;
            bean.praise = userBeanList;
            bean.comments = itemBeanList;

            mList.add(bean);
        }
    }

}
