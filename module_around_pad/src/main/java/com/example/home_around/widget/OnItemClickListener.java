package com.example.home_around.widget;

import android.view.View;

public interface OnItemClickListener {
    public void OnClick(View view, int pos);

    public void OnClick(View preView, View view, int pos);
}
