package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.AddressInfoData;
import com.example.home_around.entity.SearchTip;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressAdapter.SearchTipHolder> {

    private List<AddressInfoData>  mItems;
    private Context mContext;
    private AddressInfoData mSelected;
    private View    mPreSelect;

    public DeliveryAddressAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public SearchTipHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_delivery_address_item, viewGroup, false);
        SearchTipHolder holder = new SearchTipHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchTipHolder viewHolder, int i) {
        final AddressInfoData data = mItems.get(i);

        viewHolder.bindViewHolder(data);

        viewHolder.setOnItemClickListener(new OnSimpleItemClickListener(){
            @Override
            public void OnClick(View view, int pos) {
                super.OnClick(view, pos);
                viewHolder.check.setSelected(true);
                if (mPreSelect != null) {
                    mPreSelect.setSelected(false);
                }

                mPreSelect = viewHolder.check;
                mSelected = data;
            }
        }, i);
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.shortToast(mContext, "修改联系人");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<AddressInfoData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }


    public class SearchTipHolder extends BaseHolder<AddressInfoData> {
        public TextView name;
        public TextView phone;
        public TextView address;
        public TextView check;
        public TextView edit;

        public SearchTipHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            check = itemView.findViewById(R.id.check);
            edit = itemView.findViewById(R.id.edit);
        }

        @Override
        public void bindViewHolder(AddressInfoData data) {
            super.bindViewHolder(data);
            if (mSelected != null && mSelected.getId() == data.getId()) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }

            name.setText(data.getName() + "    " + data.getPhone());
            address.setText(data.getAddress());
        }
    }
}
