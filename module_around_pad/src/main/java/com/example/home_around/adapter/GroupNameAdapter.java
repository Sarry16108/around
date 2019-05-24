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
import com.example.home_around.entity.BrandSupplyData;
import com.example.home_around.entity.GroupNameData;
import com.example.home_around.widget.OnItemClickListener;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.lib_generic.utils.GlideUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class GroupNameAdapter extends RecyclerView.Adapter<GroupNameAdapter.GroupNameHolder> {

    private List<GroupNameData> mItems;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    //优化至底层
    private View                mPreClickView;
    private int             mDefaultSelectIndex = 0;

    public GroupNameAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public GroupNameAdapter.GroupNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_group_name_item, viewGroup, false);
        GroupNameAdapter.GroupNameHolder holder = new GroupNameAdapter.GroupNameHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupNameAdapter.GroupNameHolder viewHolder, int i) {
        GroupNameData data = mItems.get(i);

        viewHolder.bindViewHolder(data);
        if (mPreClickView == null && i == mDefaultSelectIndex) {
            mPreClickView = viewHolder.getParent();
            mPreClickView.setSelected(true);
            mPreClickView.setBackgroundResource(R.color.sfy_yellow);
        }

        if (mItemClickListener != null) {
            viewHolder.setOnItemClickListener(new OnSimpleItemClickListener() {
                @Override
                public void OnClick(View view, int pos) {
                    mItemClickListener.OnClick(mPreClickView, view, pos);
                    //保留视图
                    mPreClickView = viewHolder.getParent();
                }
            }, i);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<GroupNameData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setDefaultSelectItem(int index) {
        this.mDefaultSelectIndex = index;
    }

    /**
     * 获取组类别id
     * @param pos
     * @return
     */
    public int getItemType(int pos) {
        return mItems.get(pos).getId();
    }

    public class GroupNameHolder extends BaseHolder<GroupNameData> {
        public TextView name;

        public GroupNameHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }

        @Override
        public void bindViewHolder(GroupNameData data) {
            super.bindViewHolder(data);

            name.setText(data.getName());
        }
    }
}
