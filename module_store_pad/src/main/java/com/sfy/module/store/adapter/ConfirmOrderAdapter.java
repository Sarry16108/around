package com.sfy.module.store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.sfy.module.store.R;
import com.sfy.module.store.entity.ConfirmOrderData;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderAdapter extends RecyclerView.Adapter<ConfirmOrderAdapter.ConfirmOrderHolder> {

    private List<ConfirmOrderData>  mItems;
    private Context mContext;

    public ConfirmOrderAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public ConfirmOrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_confirm_order_item, viewGroup, false);
        ConfirmOrderHolder holder = new ConfirmOrderHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderHolder viewHolder, int i) {
        ConfirmOrderData data = mItems.get(i);

        viewHolder.bindViewHolder(data);

        viewHolder.deliveryMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.shortToast(mContext, "位置：" + i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<ConfirmOrderData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public float getTotalPrice() {
        float value = 0;
        for (ConfirmOrderData item : mItems) {
            value += item.getCount() * Float.valueOf(item.getPrice());
        }

        return value;
    }


    public static class ConfirmOrderHolder extends RecyclerView.ViewHolder {
        private LinearLayout shopPart;
        private ImageView   shopImg;
        private TextView    shopTitle;
        private RelativeLayout deliveryPart;
        public ImageView   deliveryMore;
        private TextView    deliveryInfo;
        private LinearLayout remartPart;
        private EditText    remark;


        public ImageView image;
        public TextView title;
        public TextView extInfo;
        public TextView price;
        private TextView add;

        public ConfirmOrderHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add);

            shopPart = itemView.findViewById(R.id.shop_part);
            shopImg = itemView.findViewById(R.id.shop_img);
            shopTitle = itemView.findViewById(R.id.shop_title);
            deliveryPart = itemView.findViewById(R.id.delivery_part);
            deliveryMore = itemView.findViewById(R.id.delivery_more);
            deliveryInfo = itemView.findViewById(R.id.develiry_info);
            remartPart = itemView.findViewById(R.id.remark_part);
            remark = itemView.findViewById(R.id.remark);
        }

        public void bindViewHolder(ConfirmOrderData data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());
            price.setText("¥" + data.getPrice());
            add.setText("X" + data.getCount());

            if (data.isFirst() && data.isLast()) {
                shopPart.setVisibility(View.VISIBLE);
                deliveryPart.setVisibility(View.VISIBLE);
                remartPart.setVisibility(View.VISIBLE);

                GlideUtils.showImage(shopImg, data.getShopImgUrl());
                shopTitle.setText(data.getShopName());
                deliveryInfo.setText(data.getDelivery());
            } else if (data.isLast()) {
                shopPart.setVisibility(View.GONE);

                deliveryPart.setVisibility(View.VISIBLE);
                remartPart.setVisibility(View.VISIBLE);
                deliveryInfo.setText(data.getDelivery());
            } else if (data.isFirst()) {
                shopPart.setVisibility(View.VISIBLE);
                deliveryPart.setVisibility(View.GONE);
                remartPart.setVisibility(View.GONE);

                GlideUtils.showImage(shopImg, data.getShopImgUrl());
                shopTitle.setText(data.getShopName());
            }
        }
    }
}
