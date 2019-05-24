package com.example.home_around.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class HomeItemDecoration extends RecyclerView.ItemDecoration {
    private int mVerticalSpace = 0;
    private int mHorizontalSpace = 0;

    public HomeItemDecoration(int vSpace, int hSpace) {
        mVerticalSpace = vSpace;
        mHorizontalSpace = hSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (mHorizontalSpace > 0) {
            int h = mHorizontalSpace / 2;
            outRect.left = outRect.right = h;
        }

        if (mVerticalSpace > 0) {
            int v = mVerticalSpace / 2;
            outRect.top = outRect.bottom = v;
        }
    }
}
