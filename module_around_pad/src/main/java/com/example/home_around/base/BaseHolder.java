package com.example.home_around.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.home_around.widget.OnItemClickListener;

public class BaseHolder<T> extends RecyclerView.ViewHolder {
    private View parent;

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
    }

    public void setOnItemClickListener(final OnItemClickListener listener, final int pos) {
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(v, pos);
            }
        });

    }

    public View getParent() {
        return parent;
    }

    public void bindViewHolder(T data) {

    }
}
