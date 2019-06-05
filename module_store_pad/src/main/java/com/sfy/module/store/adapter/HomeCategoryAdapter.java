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
import com.sfy.module.store.entity.HomeCategoryData;
import com.sfy.module.store.entity.HomeRecommendData;
import com.sfy.module.store.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ResultHolder> {

    private List<HomeCategoryData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public HomeCategoryAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_main_category_item, viewGroup, false);
        return new ResultHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        HomeCategoryData data = mItems.get(i);

        viewHolder.bindViewHolder(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<HomeCategoryData> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(HomeCategoryData item) {
        mItems.add(item);
    }

    public void addItems(List<HomeCategoryData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public HomeCategoryData getItem(int index) {
        return mItems.get(index);
    }



    public static class ResultHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
        }


        public void bindViewHolder(HomeCategoryData data) {
            GlideUtils.showImage(image, data.getImgUrl());
        }
    }

}
