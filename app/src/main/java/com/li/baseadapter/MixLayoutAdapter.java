package com.li.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.li.adperlibrary.base.BaseAdapter;
import com.li.adperlibrary.base.MixtureLayout;
import com.li.adperlibrary.base.OnItemClick;
import com.li.adperlibrary.base.OnLongItemClick;
import com.li.adperlibrary.base.ViewHolder;

import java.util.List;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/11 20
 * 描述：
 */
public class MixLayoutAdapter extends BaseAdapter<String> {
    private Context mContext;
    public MixLayoutAdapter(Context context, List<String> data) {
        super(context, data, new MixtureLayout<String>() {
            @Override
            public int getLayoutId(String data,int pos) {
                if ((pos+1)%2 ==0){
                    return R.layout.item_simple;
                }else {
                    return R.layout.item_simple2;
                }
            }
        });
        this.mContext = context;
    }

    @Override
    protected void bindViewHolder(ViewHolder holder, final String strings, final int position) {
//      链式调用
        holder.setText(R.id.tv_title1,strings)
                .setText(R.id.tv_title2,"哈哈哈");
//       自定义网咯图片解析方式
        holder.setImageView(R.id.iv_pic,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591887961825&di=b46faa0b1254616dd4f1a36653f36fef&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
                 , new MyImageLoader(mContext));
//       adapter 内部的点击事件
        holder.setOnItemClick(R.id.iv_pic, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
//               回调到activity中 根据type判断谁点击
                getAdapterOnItemClick().click(view,strings,1,position);
            }
        });
        holder.setOnItemClick(R.id.tv_title1, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
                getAdapterOnItemClick().click(view,strings,2,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "click: ----------" );
                getAdapterOnItemClick().click(v,strings,3,position);
            }
        });
        holder.setOnLongItemClick(R.id.iv_pic, new OnLongItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "Longclick: ----------" );
                getAdapterOnLongItemClick().click(view,strings,1,position);
            }
        });
    }


}
