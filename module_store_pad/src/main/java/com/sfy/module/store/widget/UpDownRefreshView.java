package com.sfy.module.store.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.lib_generic.utils.LogUtils;

public class UpDownRefreshView extends ScrollView {

    private boolean mIsTop = false;
    private boolean mIsBottom = false;
    private float   mRecordY = 0;

    private View    mTopView;
    private View    mBottomView;
    private View    mView;
    private RelativeLayout  mParent;
    private ViewGroup   mFirstChild;
    private int     mOriginalHight = 0;
    private int     mOriginalPadding = 0;
    //是否触发过，防止多次触发
    private boolean mTriggered = false;

    private OnRefreshListener   mRefreshListener;
    private OnLoadingListener   mLoadingListener;

    public UpDownRefreshView(Context context) {
        this(context, null);
    }

    public UpDownRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UpDownRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UpDownRefreshView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static interface OnRefreshListener {
        public void onRefresh();
    }


    public static interface OnLoadingListener {
        public void onLoading();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (clampedY) {
            mIsTop = scrollY == 0;
            mIsBottom = scrollY > 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTriggered = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mTriggered) {
                    break;
                }

                if (mRecordY == 0 && (mIsBottom || mIsTop)) {
                    mRecordY = ev.getY();
                    if (mBottomView != null) {
                        mOriginalHight = mBottomView.getHeight();
                        mOriginalPadding = mBottomView.getPaddingBottom();
                    } else if (mTopView != null){
                        mOriginalHight = mTopView.getHeight();
                        mOriginalPadding = mTopView.getPaddingTop();
                    }
                    if (mIsTop) {
                        mView = mTopView;
                    } else {
                        mView = mBottomView;
                    }
                }
                if (mView == null) {
                    break;
                }

                if (mIsTop) {
                    float dif = ev.getY() - mRecordY;
                    if (dif >= 200 && mRefreshListener != null) {
                        mTriggered = true;
                        mRefreshListener.onRefresh();
                    }
//                    ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
//                    layoutParams.height = mOriginalHight + (int) dif;
//                    mView.setLayoutParams(layoutParams);

                    mView.setPadding(mView.getPaddingLeft(), mView.getPaddingTop(), mView.getPaddingRight(), mOriginalPadding + (int)dif/2);
                } else if (mIsBottom) {
                    float dif = mRecordY - ev.getY();
                    if (dif >= 200 && mLoadingListener != null) {
                        mTriggered = true;
                        mLoadingListener.onLoading();
                    }
//                    ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
//                    layoutParams.height = mOriginalHight + (int) dif;
                    mView.setPadding(mView.getPaddingLeft(), mView.getPaddingTop(), mView.getPaddingRight(),  mOriginalPadding + (int)dif/2);
//                    mView.setLayoutParams(layoutParams);
                }
                break;
            case MotionEvent.ACTION_UP:
                mRecordY = 0;
                if (mView != null) {
//                    ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
//                    layoutParams.height = mOriginalHight;
//                    mView.setLayoutParams(layoutParams);
                    mView.setPadding(mView.getPaddingLeft(), mView.getPaddingTop(), mView.getPaddingRight(), mOriginalPadding);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    public void setTopView(View view, OnRefreshListener listener) {
        mTopView = view;
        mRefreshListener = listener;
    }

    public void setBottomView(View view, OnLoadingListener listener) {
        mBottomView = view;
        mLoadingListener = listener;
    }
}
