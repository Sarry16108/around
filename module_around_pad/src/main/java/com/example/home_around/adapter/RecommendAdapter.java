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

import com.example.module_around.R;
import com.example.lib_generic.utils.GlideUtils;
import com.example.home_around.entity.RecommendData;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendHolder> {

    private List<RecommendData> mItems;
    private Context mContext;

    public RecommendAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

        spanBig = new RelativeSizeSpan(1.5f);
    }


    @NonNull
    @Override
    public RecommendHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_recommend_item, viewGroup, false);
        RecommendHolder holder = new RecommendHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendHolder recommendHolder, int i) {
        RecommendData data = mItems.get(i);

        GlideUtils.showImage(recommendHolder.image, data.getImgUrl());
        recommendHolder.title.setText(data.getTitle());
        recommendHolder.extInfo.setText(data.getExtInfo());

        SpannableStringBuilder builder = new SpannableStringBuilder().
                append("¥").append(data.getPrice(), spanBig, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                .append("起");
        recommendHolder.price.setText(builder);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<RecommendData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    private RelativeSizeSpan spanBig;

    public static class RecommendHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView  title;
        public TextView  extInfo;
        public TextView  price;

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);
        }
    }
}
