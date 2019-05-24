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
import com.example.home_around.entity.BrandSupplyData;
import com.example.home_around.entity.MerchantsData;
import com.example.home_around.entity.RecommendData;
import com.example.home_around.widget.OnItemClickListener;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class MerchantsAdapter extends RecyclerView.Adapter<MerchantsAdapter.MerchantsdHolder> {

    private List<MerchantsData.MerchantsItemData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public MerchantsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public MerchantsdHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_merchants_item, viewGroup, false);
        MerchantsdHolder holder = new MerchantsdHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantsdHolder viewHolder, int i) {
        MerchantsData.MerchantsItemData data = mItems.get(i);

        viewHolder.bindViewHolder(data);

        if (mItemClickListener != null) {
            viewHolder.setOnItemClickListener(mItemClickListener, i);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public MerchantsData.MerchantsItemData getItem(int pos) {
        if (null == mItems && mItems.size() <= pos) {
            return null;
        }

        return mItems.get(pos);
    }

    public void setItems(List<MerchantsData.MerchantsItemData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }


    public class MerchantsdHolder extends BaseHolder {
        private ImageView image;
        private TextView title;
        private TextView  extInfo;
        private TextView  price;
        private RelativeSizeSpan spanBig;

        public MerchantsdHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);

            spanBig = new RelativeSizeSpan(1.5f);
        }


        public void bindViewHolder(MerchantsData.MerchantsItemData data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());

            SpannableStringBuilder builder = new SpannableStringBuilder().
                    append("¥").append(data.getPrice(), spanBig, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                    .append("起");
            price.setText(builder);
        }
    }

}
