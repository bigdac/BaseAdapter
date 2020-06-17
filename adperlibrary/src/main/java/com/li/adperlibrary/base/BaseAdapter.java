package com.li.adperlibrary.base;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/6 15
 * 描述：
 */
public abstract class   BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private int mLayoutId ;
    private List<T> mData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ViewHolder mViewHolder ;
    private MixtureLayout<T> mMixtureLayout ;
//   初始化Adapter的点击事件
    private AdapterOnItemClick<T> mAdapterOnItemClick = new AdapterOnItemClick<T>() {
        @Override
        public void click(View view, T t, int type, int pos) {
        }
    };
    //   初始化Adapter的长按点击事件
    private AdapterOnLongItemClick<T> mAdapterOnLongItemClick = new AdapterOnLongItemClick<T>() {
        @Override
        public void click(View view, T t, int type, int pos) {

        }
    };

    /*简单初始化*/
    public BaseAdapter(Context context, List<T>  data ,int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
        this.mLayoutInflater = LayoutInflater.from(context);
    }
    /*多布局情况下初始化*/
    public BaseAdapter(Context context, List<T>  data ,MixtureLayout<T> mixtureLayout) {
        this(context,data,-1);
        this.mMixtureLayout = mixtureLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mMixtureLayout!=null){
            mLayoutId = viewType;
        }
        if (mLayoutId== -1)
            throw new Resources.NotFoundException("没有找到该mLayoutId");
        View  view = mLayoutInflater.inflate(mLayoutId,parent,false);
//       创建ViewHolder
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if (mMixtureLayout!=null){
//           多布局情况下返回不用viewType
            return mMixtureLayout.getLayoutId(mData.get(position),position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bindViewHolder(holder,mData.get(position),position);
    }
    /*实现bindViewHolder方法*/
    protected abstract void bindViewHolder(ViewHolder holder, T t, int position);

    public void upData(List<T> t){
        this.mData = t;
        notifyDataSetChanged();
    }

    public void remove (T t){
        int index = mData.indexOf(t);
        if (index>=0){
            mData.remove(t);
            notifyItemRemoved(index);
        }
    }

    public void remove (int index){
        if (index>=0){
            mData.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void removeAll(){
        this.mData.clear();
        notifyDataSetChanged();
    }

    //    设置点击事件
    public void SetOnClickListener(AdapterOnItemClick<T> adapterOnItemClick ){
        this.mAdapterOnItemClick = adapterOnItemClick;
    };
//    获取点击事件
    public AdapterOnItemClick<T> getAdapterOnItemClick() {
           return mAdapterOnItemClick ;
    }
//    设置长点击事件
    public void SetOnLongClickListener(AdapterOnLongItemClick<T> adapterOnLongItemClick ){
        this.mAdapterOnLongItemClick = adapterOnLongItemClick;
    };

//   获取长点击事件
    public AdapterOnLongItemClick<T> getAdapterOnLongItemClick() {
            return mAdapterOnLongItemClick;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
