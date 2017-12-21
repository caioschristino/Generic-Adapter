package com.example.csanchez.genericadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by csanchez on 20/12/2017.
 */

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements Filterable {
    private Context mContext;
    private List<T> mItems;
    private List<T> mRecoveryData;

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
        this.mRecoveryData = this.mItems;
        this.notifyDataSetChanged();
    }

    public T getItem(int position) {
        return this.mItems.get(position);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setEndlessScrollLayoutManager(LinearLayoutManager manager) {
        scrollListener.mLayoutManager = manager;
    }

    public void setEndlessScroll(RecyclerView recyclerView, EndlessScrollListener.EndlessListener endlessListener) {
        if (scrollListener.mLayoutManager != null) {
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

    public void filter(Predicate<? super T> predicate) {
        if (predicate != null) {
            if (valueFilter == null) {
                getFilter();
            }

            valueFilter.predicate = predicate;
            valueFilter.performFiltering(null);
        }
    }

    private class ValueFilter extends Filter {
        Predicate<? super T> predicate;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            List<T> mStringFilterList = new ArrayList<>();
            mRecoveryData.stream()
                    .filter(predicate)
                    .forEach(item -> {
                        mStringFilterList.add(item);
                    });

            if (mStringFilterList != null && mStringFilterList.size() > 0) {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            } else {
                results.count = mItems.size();
                results.values = mItems;
            }
            publishResults(constraint, results);
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mItems = (List<T>) results.values;
            notifyDataSetChanged();
        }
    }
}