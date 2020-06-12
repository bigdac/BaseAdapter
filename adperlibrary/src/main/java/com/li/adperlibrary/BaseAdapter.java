package com.li.adperlibrary;

import android.content.Context;
import android.content.res.Resources;
import android.view.InflateException;
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

    public BaseAdapter(Context context, List<T>  data ,int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

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
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMixtureLayout!=null){
            return mMixtureLayout.getLayoutId(mData.get(position),position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bindViewHolder(holder,mData.get(position),position);

    }

    protected abstract void bindViewHolder(ViewHolder holder, T t, int position);

    public void SetOnClickListener(AdapterOnItemClick<T> adapterOnItemClick ){
        this.mAdapterOnItemClick = adapterOnItemClick;
    };
    private AdapterOnItemClick<T> mAdapterOnItemClick;

    public AdapterOnItemClick<T> getAdapterOnItemClick() {
        return mAdapterOnItemClick;
    }
    public void SetOnLongClickListener(AdapterOnLongItemClick<T> adapterOnLongItemClick ){
        this.mAdapterOnLongItemClick = adapterOnLongItemClick;
    };
    private AdapterOnLongItemClick<T> mAdapterOnLongItemClick;

    public AdapterOnLongItemClick<T> getAdapterOnLongItemClick() {
        return mAdapterOnLongItemClick;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
