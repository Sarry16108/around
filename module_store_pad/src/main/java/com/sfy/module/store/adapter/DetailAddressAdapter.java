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
import com.sfy.module.store.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class DetailAddressAdapter extends RecyclerView.Adapter<DetailAddressAdapter.ResultHolder> {

    private List<String> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private int mSelectedIndex = -1;


    public DetailAddressAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_detail_address_item, viewGroup, false);
        return new ResultHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        String data = mItems.get(i);

        viewHolder.bindViewHolder(data);
        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.check.setSelected(true);
                viewHolder.address.setSelected(true);

                notifyItemChanged(mSelectedIndex);
                notifyItemChanged(i);
                mSelectedIndex = i;
            }
        });
        viewHolder.check.setSelected(mSelectedIndex == i);
        viewHolder.address.setSelected(mSelectedIndex == i);
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

    public int getSelectItem() {
        return mSelectedIndex;
    }

    public String getSelectedAddress() {
        if (mSelectedIndex < 0) {
            return "";
        }
        return mItems.get(mSelectedIndex);
    }


    public static class ResultHolder extends RecyclerView.ViewHolder {
        public ImageView check;
        public TextView address;
        public View     parent;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
            check = itemView.findViewById(R.id.check);
            address = itemView.findViewById(R.id.address);
        }


        public void bindViewHolder(String data) {
            address.setText(data);
        }
    }

}
