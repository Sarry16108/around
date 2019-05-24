package com.example.home_around.adapter;

import android.content.Context;
import android.print.PrinterId;
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


public class WeekdaySelectorTimeAdapter extends RecyclerView.Adapter<WeekdaySelectorTimeAdapter.DayHolder> {

    private List<String> mItems;
    private Context mContext;
    private String mSelectedTime;
    private View    mPreSel;

    public WeekdaySelectorTimeAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(10);

    }

    @NonNull
    @Override
    public DayHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_next_time_tag_item, viewGroup, false);
        DayHolder holder = new DayHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DayHolder viewHolder, int i) {
        final String data = mItems.get(i);

        viewHolder.bindViewHolder(data);

            viewHolder.setOnItemClickListener(new OnSimpleItemClickListener() {
                @Override
                public void OnClick(View view, int pos) {
                    super.OnClick(view, pos);
                    view.setSelected(true);
//                    view.setBackgroundResource(R.drawable.shape_circle_stroke_yellow);
                    if (mPreSel != null) {
                        mPreSel.setSelected(false);
//                        view.setBackgroundResource(R.drawable.shape_circle_stroke_gray);
                    }

                    mPreSel = view;
                    mSelectedTime = data;
                }
            }, i);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public void setItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);

        notifyDataSetChanged();
    }

    public String getSelectedTime() {
        return mSelectedTime;
    }

    public class DayHolder extends BaseHolder<String> {
        public TextView time;

        public DayHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time);
        }

        @Override
        public void bindViewHolder(String data) {
            super.bindViewHolder(data);

            time.setText(data);
        }
    }
}
