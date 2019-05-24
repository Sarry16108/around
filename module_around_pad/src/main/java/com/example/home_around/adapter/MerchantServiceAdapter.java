package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.MerchantServiceData;
import com.example.home_around.widget.OnItemClickListener;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class MerchantServiceAdapter extends RecyclerView.Adapter<MerchantServiceAdapter.MerchantServiceHolder> {

    private List<MerchantServiceData.MerchantServiceItemData> mItems;
    private Context mContext;

    private OnItemClickListener mItemClickListener;
    private OnItemClickListener mAddClickListener;

    private RecyclerView    mRecyclerView;

    public MerchantServiceAdapter(Context context, RecyclerView view) {
        mContext = context;
        mItems = new ArrayList<>(10);
        mRecyclerView = view;
    }

    @NonNull
    @Override
    public MerchantServiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_merchant_service_data_item, viewGroup, false);
        MerchantServiceHolder holder = new MerchantServiceHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantServiceHolder viewHolder, final int i) {
        MerchantServiceData.MerchantServiceItemData data = mItems.get(i);

        viewHolder.bindViewHolder(data);
        if (mItemClickListener != null) {
            viewHolder.setOnItemClickListener(mItemClickListener, i);
        }

        if (mAddClickListener != null) {
            viewHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddClickListener.OnClick(v, i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<MerchantServiceData.MerchantServiceItemData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnAddClickListener(OnItemClickListener listener) {
        mAddClickListener = listener;
    }

    public MerchantServiceData.MerchantServiceItemData getItem(int pos) {
        return mItems.get(pos);
    }

    public class MerchantServiceHolder extends BaseHolder<MerchantServiceData.MerchantServiceItemData> {
        public ImageView    image;
        public TextView title;
        public TextView price;
        public TextView add;


        public MerchantServiceHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add);
        }

        @Override
        public void bindViewHolder(MerchantServiceData.MerchantServiceItemData data) {
            super.bindViewHolder(data);
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            price.setText(data.getPrice());
        }
    }
}
