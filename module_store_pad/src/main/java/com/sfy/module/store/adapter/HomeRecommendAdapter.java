package com.sfy.module.store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_generic.utils.GlideUtils;
import com.sfy.module.store.R;
import com.sfy.module.store.entity.HomeRecommendData;
import com.sfy.module.store.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ResultHolder> {

    private List<HomeRecommendData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public HomeRecommendAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_main_recommend_item, viewGroup, false);
        return new ResultHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        HomeRecommendData data = mItems.get(i);
        viewHolder.bindViewHolder(data);

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.OnClick(viewHolder.parent, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<HomeRecommendData> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(HomeRecommendData item) {
        mItems.add(item);
    }

    public void addItems(List<HomeRecommendData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public HomeRecommendData getItem(int index) {
        return mItems.get(index);
    }



    public static class ResultHolder extends RecyclerView.ViewHolder {
        public View    parent;
        private ImageView image;
        private TextView  title;
        private TextView  price;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }


        public void bindViewHolder(HomeRecommendData data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            price.setText(data.getPrice());
        }
    }

}
