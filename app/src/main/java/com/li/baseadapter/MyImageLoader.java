package com.li.baseadapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.li.adperlibrary.base.ViewHolder;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/11 19
 * 描述：
 */
public class MyImageLoader implements ViewHolder.ImageLoader {
    private Context mContext;

    public MyImageLoader(Context context) {
        this.mContext = context;
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
//      实现MyImageLoader 方法自定义网咯图片的解析方法更容易扩展
        Glide.with(mContext).load(url).into(imageView);
    }
}
