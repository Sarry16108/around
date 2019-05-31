package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.BrandSupplyData;
import com.example.home_around.entity.GroupData;
import com.example.home_around.widget.OnItemClickListener;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class GroupDataAdapter extends RecyclerView.Adapter<GroupDataAdapter.GroupDataHolder> {

    private List<GroupData.GroupItemData> mItems;
    private Context mContext;

    private OnItemClickListener mItemClickListener;
    private OnItemClickListener mAddClickListener;

    private RecyclerView    mRecyclerView;

    public GroupDataAdapter(Context context, RecyclerView view) {
        mContext = context;
        mItems = new ArrayList<>(10);
        mRecyclerView = view;
    }

    @NonNull
    @Override
    public GroupDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_group_data_item, viewGroup, false);
        GroupDataHolder holder = new GroupDataHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupDataHolder viewHolder, final int i) {
        GroupData.GroupItemData data = mItems.get(i);

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


    public void setItems(List<GroupData.GroupItemData> items) {
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

    public void scrollToType(int type) {
        int pos = 0;
        for (GroupData.GroupItemData item : mItems) {
            if (item.getType() == type) {
                break;
            }

            pos++;
        }

//        LinearLayoutManager layoutManager = ((LinearLayoutManager)mRecyclerView.getLayoutManager());
//        int firstItem = layoutManager.findFirstVisibleItemPosition();
//        int top ;
//            top = layoutManager.getChildAt(pos).getTop() - layoutManager.getChildAt(firstItem).getTop();
//        mRecyclerView.scrollBy(0, top);

        mRecyclerView.smoothScrollToPosition(pos);
    }

    public GroupData.GroupItemData getItem(int pos) {
        return mItems.get(pos);
    }

    public class GroupDataHolder extends BaseHolder<GroupData.GroupItemData> {
        public ImageView    image;
        public TextView title;
        public TextView extInfo;
        public TextView price;
        public TextView add;


        public GroupDataHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            extInfo = itemView.findViewById(R.id.ext_info);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add);
        }

        @Override
        public void bindViewHolder(GroupData.GroupItemData data) {
            super.bindViewHolder(data);
            GlideUtils.showImage(image, data.getImgUrl());
            title.setText(data.getTitle());
            extInfo.setText(data.getExtInfo());
            price.setText(data.getPrice());
        }
    }
}
