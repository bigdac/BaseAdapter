package com.li.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.li.adperlibrary.AdapterOnItemClick;
import com.li.adperlibrary.BaseAdapter;
import com.li.adperlibrary.OnItemClick;
import com.li.adperlibrary.ViewHolder;

import java.util.List;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/11 20
 * 描述：
 */
public class SimpleAdapter extends BaseAdapter<String> {
    private Context mContext;
    public SimpleAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }

    @Override
    protected void bindViewHolder(ViewHolder holder, final String strings, final int position) {
        holder.setText(R.id.tv_title1,strings)
                .setText(R.id.tv_title2,"哈哈哈");
        holder.setImageView(R.id.iv_pic,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591887961825&di=b46faa0b1254616dd4f1a36653f36fef&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"
                 , new MyImageLoader(mContext));
        holder.setOnItemClick(R.id.iv_pic, new OnItemClick() {
            @Override
            public void click(View view) {
                Log.e("TAG", "click: ----------" );
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
    }


}
