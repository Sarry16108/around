package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_around.R;
import com.example.lib_generic.utils.GlideUtils;
import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.CategoryData;
import com.example.home_around.entity.RecommendData;
import com.example.home_around.entity.SearchTip;

import java.util.ArrayList;
import java.util.List;

public class SearchTipAdapter extends RecyclerView.Adapter<SearchTipAdapter.SearchTipHolder> {

    private List<SearchTip.SearchTipItem>  mItems;
    private Context mContext;

    public SearchTipAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public SearchTipHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_tip_item, viewGroup, false);
        SearchTipHolder holder = new SearchTipHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTipHolder viewHolder, int i) {
        SearchTip.SearchTipItem data = mItems.get(i);

        viewHolder.bindViewHolder(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<SearchTip.SearchTipItem> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }


    public static class SearchTipHolder extends BaseHolder<SearchTip.SearchTipItem> {
        public TextView tip;
        public TextView tag;

        public SearchTipHolder(@NonNull View itemView) {
            super(itemView);

            tip = itemView.findViewById(R.id.tip);
            tag = itemView.findViewById(R.id.tag);
        }

        @Override
        public void bindViewHolder(SearchTip.SearchTipItem data) {
            super.bindViewHolder(data);

            tip.setText(data.getTip());
            switch (data.getType()) {
                case 1:
                    tip.setText("商家");
                    break;
            }
        }
    }
}
