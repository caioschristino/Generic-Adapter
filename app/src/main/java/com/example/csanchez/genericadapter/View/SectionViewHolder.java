package com.example.csanchez.genericadapter.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.csanchez.genericadapter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by csanchez on 21/12/2017.
 */

public class SectionViewHolder  extends RecyclerView.ViewHolder {
    private View mView;
    private Context mContext;
    @BindView(R.id.tx_name)
    TextView name;

    public SectionViewHolder(Context context, View view) {
        super(view);

        this.mView = view;
        this.mContext = context;
        ButterKnife.bind(this, this.mView);
    }

    public void setName(String name) {
        if (this.name != null) {
            this.name.setText(name);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        if(listener != null){
            this.mView.setOnClickListener(listener);
        }
    }
}
