package com.li.adperlibrary;

import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/6 15
 * 描述：
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray <WeakReference<View>> mViews;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId){
        WeakReference<View> view =  mViews.get(viewId);
        View view1 = null;
        if (view!=null){
             view1 = view.get();
        }else {
            view1 = itemView.findViewById(viewId);
            mViews.put(viewId,new WeakReference<View>(view1));
        }
        return (T) view1;
    }

    public ViewHolder setText (int viewId ,CharSequence charSequence){
        TextView view = getView(viewId);
        if (view==null){
            throw new InflateException("设置文字时没有找到该View");
        }else {
            view.setText(charSequence);
        }
        return  this;
    }
//    加载本地图片
    public ViewHolder setImageView (int viewId ,int resId){
        ImageView view = getView(viewId);
        if (view==null){
            throw new InflateException("设置图片时没有找到该View");
        }else {
            view.setImageResource(resId);
        }
        return  this;
    }
//    加载网络图片
    public ViewHolder setImageView (int viewId ,String url,ImageLoader imageLoader){
        ImageView view = getView(viewId);
        if (view==null){
            throw new InflateException("设置图片时没有找到该View");
        }else {
            imageLoader.loadImage(view,url);
        }
        return  this;
    }

//    private OnItemClick onItemClick;
    public void setOnItemClick(int viewId, final OnItemClick onItemClick){
        View view = getView(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.click(v);
            }
        });
    }

    public void setOnLongItemClick(int viewId, final OnLongItemClick onLongItemClick){
        View view = getView(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLongItemClick.click(v);
            }
        });
    }
//  接口 扩展使用各种网络图片的使用

    public interface ImageLoader{
        void  loadImage(ImageView imageView ,String url);
    }

}
