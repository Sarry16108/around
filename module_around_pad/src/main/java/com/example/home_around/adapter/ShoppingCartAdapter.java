package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.ShoppingBaseItemData;
import com.example.home_around.entity.ShoppingCartData;
import com.example.home_around.entity.ShoppingCartDividerTagData;
import com.example.home_around.widget.AmountView;
import com.example.home_around.widget.OnItemClickListener;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartAdapter extends RecyclerView.Adapter<BaseHolder> {

    private List<ShoppingBaseItemData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private LongSparseArray<Float> mSelected;
    private float   mPrice;
    private TextView    mPriceView;


    public ShoppingCartAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
        mSelected = new LongSparseArray<>(15);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }


    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        switch (i) {

            case ShoppingBaseItemData.TYPE_DIVIDER_TAG:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_shopping_cart_tag_item, viewGroup, false);
                return new ShoppingCartTagHolder(view);

            case ShoppingBaseItemData.TYPE_DATA:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_shopping_cart_data_item, viewGroup, false);
                return new ShoppingCartDataHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder viewHolder, int i) {
        ShoppingBaseItemData data = mItems.get(i);

        if (viewHolder instanceof ShoppingCartDataHolder) {
            ((ShoppingCartDataHolder)viewHolder).bindViewHolder((ShoppingCartData.ShoppingCartItem) data);
        }else if (viewHolder instanceof ShoppingCartTagHolder) {
            ((ShoppingCartTagHolder)viewHolder).bindViewHolder((ShoppingCartDividerTagData) data);
        }

        if (mItemClickListener != null) {
            viewHolder.setOnItemClickListener(mItemClickListener, i);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<ShoppingBaseItemData> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(ShoppingBaseItemData item) {
        mItems.add(item);
    }

    public void addItems(List<ShoppingBaseItemData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public ShoppingBaseItemData getItem(int index) {
        return mItems.get(index);
    }

    public void setPriceUpdateView(TextView textView) {
        mPriceView = textView;
    }

    public void selectAll(boolean selectAll) {
        if (selectAll) {
            for (ShoppingBaseItemData item : mItems) {
                if (ShoppingBaseItemData.TYPE_DATA != item.getType()) {
                    continue;
                }
                ShoppingCartData.ShoppingCartItem cartItem = (ShoppingCartData.ShoppingCartItem) item;
                mSelected.put(cartItem.getId(), cartItem.getPrice() * cartItem.getCount());
            }
        } else {
            mSelected.clear();
        }

        caculatePrice();
        notifyDataSetChanged();
    }


    private void caculatePrice() {
        mPrice = 0;
        for (int i = 0; i < mSelected.size(); ++i) {
            mPrice += mSelected.valueAt(i);
        }

        if (mPriceView != null) {
            mPriceView.setText(String.valueOf(mPrice));
        } else {
            mPriceView.setText(0);
        }
    }

    public class ShoppingCartDataHolder extends BaseHolder {
        private TextView  check;
        private ImageView image;
        private TextView  title;
        private TextView  sizeColor;
        private TextView  price;
        private AmountView amountView;

        public ShoppingCartDataHolder(@NonNull View itemView) {
            super(itemView);
            check = itemView.findViewById(R.id.check);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            sizeColor = itemView.findViewById(R.id.size_color);
            price = itemView.findViewById(R.id.price);
            amountView = itemView.findViewById(R.id.amount_view);
        }


        public void bindViewHolder(final ShoppingCartData.ShoppingCartItem data) {
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            check.setSelected(mSelected.indexOfKey(data.getId()) >= 0);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check.setSelected(!check.isSelected());
                    if (check.isSelected()) {
                        mSelected.put(data.getId(), amountView.getCount() * data.getPrice());
                    } else {
                        mSelected.remove(data.getId());
                    }

                    caculatePrice();
                }
            });
            sizeColor.setText(data.getSize() + "/" + data.getColor());
            price.setText("" + data.getPrice());
            amountView.setGoodsStorage(data.getStorage());
            amountView.setCount(data.getCount());
            amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    if (check.isSelected()) {
                        mSelected.put(data.getId(), amount * data.getPrice());
                        caculatePrice();
                    }

                }
            });
        }
    }


    public class ShoppingCartTagHolder extends BaseHolder {
        private TextView tag;

        public ShoppingCartTagHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
        }

        public void bindViewHolder(ShoppingCartDividerTagData data) {
            tag.setText(data.getTag());
        }
    }

}
