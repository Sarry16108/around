package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.DeliverySelectedGoodsData;
import com.example.home_around.entity.SearchTip;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class DeliverySelectedGoodsAdapter extends RecyclerView.Adapter<DeliverySelectedGoodsAdapter.SearchTipHolder> {

    private List<DeliverySelectedGoodsData>  mItems;
    private Context mContext;

    public DeliverySelectedGoodsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public SearchTipHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_delivery_selected_goods_item, viewGroup, false);
        SearchTipHolder holder = new SearchTipHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTipHolder viewHolder, int i) {
        DeliverySelectedGoodsData data = mItems.get(i);

        viewHolder.bindViewHolder(data);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<DeliverySelectedGoodsData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public float getTotalPrice() {
        float value = 0;
        for (DeliverySelectedGoodsData item : mItems) {
            value += item.getCount() * Float.valueOf(item.getPrice());
        }

        return value;
    }


    public static class SearchTipHolder extends BaseHolder<DeliverySelectedGoodsData> {
        public ImageView image;
        public TextView title;
        public TextView extInfo;
        public TextView price;
        private TextView add;

        public SearchTipHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add);
        }

        @Override
        public void bindViewHolder(DeliverySelectedGoodsData data) {
            super.bindViewHolder(data);

            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());
            SpannableStringBuilder builder = new SpannableStringBuilder().
                    append("¥").append(data.getPrice(), new RelativeSizeSpan(1.5f), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            price.setText(builder);
            add.setText("X" + data.getCount());
        }
    }
}
