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
import com.sfy.module.store.entity.DetailParamsData;
import com.sfy.module.store.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class DetailParamsAdapter extends RecyclerView.Adapter<DetailParamsAdapter.ResultHolder> {

    private List<DetailParamsData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public DetailParamsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_detail_params_item, viewGroup, false);
        return new ResultHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        DetailParamsData data = mItems.get(i);

        viewHolder.bindViewHolder(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<DetailParamsData> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(DetailParamsData item) {
        mItems.add(item);
    }

    public void addItems(List<DetailParamsData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public DetailParamsData getItem(int index) {
        return mItems.get(index);
    }



    public static class ResultHolder extends RecyclerView.ViewHolder {
        private TextView tag;
        private TextView value;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);

            tag = itemView.findViewById(R.id.tag);
            value = itemView.findViewById(R.id.value);
        }


        public void bindViewHolder(DetailParamsData data) {
            tag.setText(data.getTag());
            value.setText(data.getValue());
        }
    }

}
