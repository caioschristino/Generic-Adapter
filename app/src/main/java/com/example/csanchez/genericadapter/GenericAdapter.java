package com.example.csanchez.genericadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csanchez on 20/12/2017.
 */

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable {
    private Context mContext;
    private List<T> mItems;
    private List<T> mStringFilterList;
    private ValueFilter valueFilter;

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, final T item);

    public GenericAdapter(Context context) {
        this.mContext = context;
        this.mItems = new ArrayList<>();
        this.scrollListener.mLayoutManager = null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = setViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, this.mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public void addItems(List<T> items) {
        if (this.mItems.size() == 0) {
            this.mItems.addAll(items);
        } else {
            this.mItems.addAll(this.mItems.size(), items);
        }
        this.notifyDataSetChanged();
    }

    public T getItem(int position) {
        return this.mItems.get(position);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setLayoutManager(LinearLayoutManager manager) {
        scrollListener.mLayoutManager = manager;
    }

    public void setEndlessScroll(RecyclerView recyclerView, EndlessScrollListener.EndlessListener endlessListener) {
        if(scrollListener.mLayoutManager != null){
            scrollListener.setEndlessListener(endlessListener);
            recyclerView.addOnScrollListener(scrollListener);
        }
    }

    private EndlessScrollListener scrollListener = new EndlessScrollListener() {
        @Override
        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            if (this.mEndlessListener != null) {
                this.mEndlessListener.onLoadMore(page, totalItemsCount);
            }
        }
    };

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                //for (int i = 0; i < mItems.size(); i++) {
                //if ((mItems.get(i).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                //filterList.add(mStringFilterList.get(i));
                //}
                // }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mItems = (List<T>)results.values;
            notifyDataSetChanged();
        }
    }
}