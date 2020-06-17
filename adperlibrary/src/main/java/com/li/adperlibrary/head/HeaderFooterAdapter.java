package com.li.adperlibrary.head;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/13 14
 * 描述：
 */
public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mAdapter;

    private SparseArray<View> mHeaders ,mFooters;

    private static int BASE_HEADER_KEY = 1000000;

    private static int BASE_FOOTER_KEY = 2000000;

    public HeaderFooterAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mHeaders = new SparseArray<>();
        mFooters = new SparseArray<>();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaders.indexOfKey(viewType)>=0){
            return createFooterHeaderViewHolder(mHeaders.get(viewType));
        }else if (mFooters.indexOfKey(viewType)>=0){
            return createFooterHeaderViewHolder(mFooters.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent,viewType);
    }

    private RecyclerView.ViewHolder createFooterHeaderViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {

        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int numHeaders = mHeaders.size();
        if (position < numHeaders) {
            return ;
        }
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                 mAdapter.onBindViewHolder(holder,adjPosition);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = mHeaders.size();
        if (position < numHeaders) {
            return mHeaders.keyAt(position);
        }
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                Log.e("TAG", "getItemViewType: -->"+adjPosition +"....."+adapterCount );
                return mAdapter.getItemViewType(adjPosition);
            }
        }

        return mFooters.keyAt(adjPosition-adapterCount);
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount()+mFooters.size()+mHeaders.size();
    }

    public void addHeaderView(View view){
        if (mHeaders.indexOfValue(view)==-1){
            mHeaders.put(BASE_HEADER_KEY++,view);
        }
    }

    public void addFooterView(View view){
        if (mFooters.indexOfValue(view)==-1){
            mFooters.put(BASE_FOOTER_KEY++,view);
        }
    }

    public void removeHeaderView(View view){
        if (mHeaders.indexOfValue(view)>=0){
            mHeaders.removeAt(mHeaders.indexOfValue(view));
            notifyDataSetChanged();
        }
    }

    public void removeFooterView(View view){
        if (mFooters.indexOfValue(view)>=0){
            mFooters.removeAt(mFooters.indexOfValue(view));
            notifyDataSetChanged();
        }
    }
}
