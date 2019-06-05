package com.sfy.module.store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lib_generic.utils.GlideUtils;
import com.sfy.module.store.R;
import com.sfy.module.store.entity.HomeCategoryData;
import com.sfy.module.store.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class DetailPicsAdapter extends RecyclerView.Adapter<DetailPicsAdapter.ResultHolder> {

    private List<String> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public DetailPicsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_detail_pics_item, viewGroup, false);
        return new ResultHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        String data = mItems.get(i);

        viewHolder.bindViewHolder(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(String item) {
        mItems.add(item);
    }

    public void addItems(List<String> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public String getItem(int index) {
        return mItems.get(index);
    }



    public static class ResultHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
        }


        public void bindViewHolder(String data) {
            GlideUtils.showImage(image, data);
        }
    }

}
