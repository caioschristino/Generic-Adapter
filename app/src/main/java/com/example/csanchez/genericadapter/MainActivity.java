package com.example.csanchez.genericadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csanchez.genericadapter.Model.Menu;
import com.example.csanchez.genericadapter.View.ItemViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.menu_recycle)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.addItems(Menu.getItens());
    }

    private GenericAdapter<Menu> adapter = new GenericAdapter<Menu>(this) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view_holder, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(getContext(), view);
            return viewHolder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder, final Menu item) {

            ItemViewHolder view;
            if(holder instanceof ItemViewHolder){
                view = (ItemViewHolder)holder;
                view.setName(item.getName());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openAnotherActivity(item);
                    }
                });
            }
        }
    };

    public void openAnotherActivity(Menu item){
        Intent intent = null;
        switch (item.getName()) {
            case "Lista simples":
                intent = new Intent(this, SampleListActivity.class);
                break;
            case "Lista infinita":
                intent = new Intent(this, InfiniteListActivity.class);
                break;
            case "Lista com sessões":
                intent = new Intent(this, ListWithSectionActivity.class);
                break;
            case "Filtrando uma lista":
                intent = new Intent(this, FilterListActivity.class);
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
