package com.example.csanchez.genericadapter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csanchez.genericadapter.Model.AnyModel;
import com.example.csanchez.genericadapter.View.ItemViewHolder;
import com.example.csanchez.genericadapter.View.SectionViewHolder;

import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by csanchez on 20/12/2017.
 */

public class FilterListActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private GenericAdapter<AnyModel> adapter = new GenericAdapter<AnyModel>(this) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            if (viewType == SECTION_TYPE) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view_holder, parent, false);
                viewHolder = new ItemViewHolder(getContext(), view);
            } else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.section_view_holder, parent, false);
                viewHolder = new SectionViewHolder(getContext(), view);
            }
            return viewHolder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder, AnyModel item) {

            ItemViewHolder view;
            if (holder instanceof ItemViewHolder) {
                view = (ItemViewHolder) holder;
                view.setName(item.getName());
            }
        }
    };

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        adapter.filter(anyModel -> {
            return anyModel.getName().toLowerCase().contains(query.toLowerCase());
        });
        return true;
    }
}
