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
import com.example.home_around.entity.DeliverySelectedGoodsData;
import com.example.home_around.entity.MyOrderData;
import com.example.home_around.widget.OnItemClickListener;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.SearchTipHolder> {

    private List<MyOrderData>  mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public MyOrderAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public SearchTipHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_my_order_item, viewGroup, false);
        SearchTipHolder holder = new SearchTipHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTipHolder viewHolder, final int i) {
        MyOrderData data = mItems.get(i);

        viewHolder.bindViewHolder(data);
        if (View.VISIBLE == viewHolder.cancelOrder.getVisibility()) {
            viewHolder.cancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d((String) v.getTag());
                    mOnItemClickListener.OnClick(v, i);
                }
            });
        }
        if (View.VISIBLE == viewHolder.checkDelivery.getVisibility()) {
            viewHolder.checkDelivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.d((String) v.getTag());
                    mOnItemClickListener.OnClick(v, i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<MyOrderData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public MyOrderData getItem(int pos) {
        return mItems.get(pos);
    }

    public static class SearchTipHolder extends BaseHolder<MyOrderData> {
        public TextView merchantName;
        public ImageView image;
        public TextView title;
        public TextView extInfo;
        public TextView price;
        public TextView count;
        public TextView state;
        public TextView totalPrice;
        public TextView cancelOrder;
        public TextView checkDelivery;

        public SearchTipHolder(@NonNull View itemView) {
            super(itemView);

            merchantName = itemView.findViewById(R.id.merchant_name);
            state = itemView.findViewById(R.id.buy_state);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);
            count = itemView.findViewById(R.id.count);
            totalPrice = itemView.findViewById(R.id.bottom_price);
            cancelOrder = itemView.findViewById(R.id.cancel_order);
            checkDelivery = itemView.findViewById(R.id.check_delivery);
        }

        @Override
        public void bindViewHolder(MyOrderData data) {
            super.bindViewHolder(data);
            merchantName.setText(data.getMerchantName());
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());
            price.setText("¥" + data.getPrice());
            totalPrice.setText("¥" + data.getPrice());
            count.setText("X" + data.getCount());

            switch (data.getType()) {
                case 0:
                    state.setText("等待付款");
                    state.setSelected(true);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("取消订单");
                    checkDelivery.setVisibility(View.VISIBLE);
                    checkDelivery.setText("立即付款");
                    checkDelivery.setSelected(true);
                    break;
                case 1:
                    state.setText("商家已接单");
                    state.setSelected(true);
                    cancelOrder.setText("查看物流");
                    cancelOrder.setVisibility(View.VISIBLE);
                    checkDelivery.setText("确认收货");
                    checkDelivery.setVisibility(View.VISIBLE);
                    checkDelivery.setSelected(true);
                    break;
                case 2:
                    state.setText("未消费");
                    state.setSelected(true);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("查看券码");
                    checkDelivery.setVisibility(View.GONE);
                    break;
                case 3:
                    state.setText("已完成");
                    state.setSelected(false);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("评价");
                    checkDelivery.setVisibility(View.VISIBLE);
                    checkDelivery.setText("再次购买");
                    break;
                case 4:
                    state.setText("已取消");
                    state.setSelected(false);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("删除订单");
                    checkDelivery.setVisibility(View.VISIBLE);
                    checkDelivery.setText("重新购买");
                    break;
                case 5:
                    state.setText("未到店");
                    state.setSelected(true);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("查看详情");
                    checkDelivery.setVisibility(View.GONE);
                    break;
                case 6:
                    state.setText("订单已送达");
                    state.setSelected(false);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("再来一单");
                    checkDelivery.setVisibility(View.VISIBLE);
                    checkDelivery.setText("评价");
                    checkDelivery.setSelected(true);
                    break;
                case 7:
                    state.setText("尽快送达");
                    state.setSelected(true);
                    cancelOrder.setVisibility(View.VISIBLE);
                    cancelOrder.setText("查看物流");
                    checkDelivery.setVisibility(View.VISIBLE);
                    checkDelivery.setText("确认送达");
                    checkDelivery.setSelected(true);
                    break;
            }
        }
    }
}
