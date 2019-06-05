package com.sfy.module.store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sfy.module.store.R;
import com.sfy.module.store.entity.DetailSpecificationData;
import com.sfy.module.store.widget.OnItemClickListener;
import com.sfy.module.store.widget.flowlayout.FlowLayout;
import com.sfy.module.store.widget.flowlayout.TagAdapter;
import com.sfy.module.store.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;


public class DetailSpecificationAdapter extends RecyclerView.Adapter<DetailSpecificationAdapter.ResultHolder> {

    private List<DetailSpecificationData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;


    public DetailSpecificationAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_detail_specification_item, viewGroup, false);
        return new ResultHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        DetailSpecificationData data = mItems.get(i);

        viewHolder.bindViewHolder(data);

        TagAdapter mAdapter = new TagAdapter<String>(data.getItems()) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_tag_selector_view, viewHolder.tags, false);
                tv.setText(s);
                return tv;

            }

        };
//        viewHolder.tags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                view.setSelected(!view.isSelected());
//                return true;
//            }
//        });
        viewHolder.tags.setMaxSelectCount(1);
        viewHolder.tags.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<DetailSpecificationData> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(DetailSpecificationData item) {
        mItems.add(item);
    }

    public void addItems(List<DetailSpecificationData> items) {
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public DetailSpecificationData getItem(int index) {
        return mItems.get(index);
    }



    public static class ResultHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView tip;
        public TagFlowLayout tags;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            tip = itemView.findViewById(R.id.tip);
            tags = itemView.findViewById(R.id.tags);
        }


        public void bindViewHolder(DetailSpecificationData data) {
            name.setText(data.getName());
            tip.setText(data.getTip());
        }
    }

}
