package com.example.home_around.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home_around.base.BaseHolder;
import com.example.home_around.entity.WeekDayData;
import com.example.home_around.widget.OnItemClickListener;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;


public class WeekdaySelectorAdapter extends RecyclerView.Adapter<WeekdaySelectorAdapter.WeekdayHolder> {

    private List<WeekDayData> mItems;
    private Context mContext;
    private WeekDayData mSelectedData;
    private WeekdayHolder   mPreHolder;

    public WeekdaySelectorAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public WeekdayHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_next_week_select_item, viewGroup, false);
        WeekdayHolder holder = new WeekdayHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final WeekdayHolder viewHolder, int i) {
        final WeekDayData data = mItems.get(i);

        viewHolder.bindViewHolder(data);

            viewHolder.setOnItemClickListener(new OnSimpleItemClickListener() {
                @Override
                public void OnClick(View view, int pos) {
                    super.OnClick(view, pos);
                    viewHolder.day.setSelected(true);
                    viewHolder.weekday.setSelected(true);
                    if (mPreHolder != null) {
                        mPreHolder.day.setSelected(false);
                        mPreHolder.weekday.setSelected(false);
                    }

                    mPreHolder = viewHolder;
                    mSelectedData = data;
                }
            }, i);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<WeekDayData> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public WeekDayData getSelectedData() {
        return mSelectedData;
    }

    public class WeekdayHolder extends BaseHolder<WeekDayData> {
        public TextView weekday;
        public TextView day;

        public WeekdayHolder(@NonNull View itemView) {
            super(itemView);

            weekday = itemView.findViewById(R.id.weekday);
            day = itemView.findViewById(R.id.day);
        }

        @Override
        public void bindViewHolder(WeekDayData data) {
            super.bindViewHolder(data);

            weekday.setText(data.getWeekDay());
            day.setText(data.getDay());
        }
    }
}
