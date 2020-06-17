package com.li.adperlibrary.head;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/13 17
 * 描述：
 */
public class HeaderRecycleView extends RecyclerView {
    private HeaderFooterAdapter mAdapter;
    private  AdapterDataObserver mAdapterDataObserver  = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeChanged(positionStart, itemCount, null);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            mAdapter.notifyItemRangeChanged(positionStart,itemCount,payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeInserted(positionStart,itemCount);

        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mAdapter.notifyItemRangeRemoved(positionStart,itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mAdapter.notifyItemMoved(fromPosition,toPosition);
        }
    };
    public HeaderRecycleView(@NonNull Context context) {
        super(context);
    }

    public HeaderRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        if (adapter instanceof HeaderFooterAdapter){
            mAdapter = (HeaderFooterAdapter) adapter;
        }else {
            mAdapter = new HeaderFooterAdapter(adapter);
            mAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        }
        super.setAdapter(adapter);
    }

    public void addHeaderView(View view){
        if (mAdapter!=null){
            mAdapter.addHeaderView(view);
        }
    }

    public void addFooterView(View view){
        if (mAdapter!=null){
            mAdapter.addFooterView(view);
        }
    }

    public void removeHeaderView(View view){
        if (mAdapter!=null){
            mAdapter.addHeaderView(view);
        }
    }

    public void removeFooterView(View view){
        if (mAdapter!=null){
            mAdapter.removeFooterView(view);
        }
    }
}
