package com.example.csanchez.genericadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csanchez.genericadapter.Model.AnyModel;
import com.example.csanchez.genericadapter.View.ItemViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by csanchez on 20/12/2017.
 */

public class FilterListActivity extends AppCompatActivity {
    @BindView(R.id.anymodel_recycle)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anylist_activity);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.addItems(AnyModel.getItens());
    }

    private GenericAdapter<AnyModel> adapter = new GenericAdapter<AnyModel>(this) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view_holder, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(getContext(), view);
            return viewHolder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder, AnyModel item) {

            ItemViewHolder view;
            if(holder instanceof ItemViewHolder){
                view = (ItemViewHolder)holder;
                view.setName(item.getName());
            }
        }
    };
}
