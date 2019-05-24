package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.SearchResultData;
import com.example.home_around.entity.SearchTip;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultHolder> {

    private List<SearchResultData.SearchResultItem>  mItems;
    private Context mContext;

    public SearchResultAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_result_item, viewGroup, false);
        SearchResultHolder holder = new SearchResultHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder viewHolder, int i) {
        SearchResultData.SearchResultItem data = mItems.get(i);

        viewHolder.bindViewHolder(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<SearchResultData.SearchResultItem> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }


    public static class SearchResultHolder extends BaseHolder<SearchResultData.SearchResultItem> {
        public TextView tip;
        public TextView tag;

        public SearchResultHolder(@NonNull View itemView) {
            super(itemView);

            tip = itemView.findViewById(R.id.tip);
            tag = itemView.findViewById(R.id.tag);
        }

        @Override
        public void bindViewHolder(SearchResultData.SearchResultItem data) {
            super.bindViewHolder(data);

            tip.setText(data.getTitle());
        }
    }
}
