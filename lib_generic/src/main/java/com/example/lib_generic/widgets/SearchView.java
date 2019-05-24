package com.example.lib_generic.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_generic.R;
import com.example.lib_generic.utils.KeyboardUtils;
import com.example.lib_generic.utils.LogUtils;

public class SearchView extends LinearLayout {

    private EditText mText;
    private Context mContext;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


    public void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        int theme = typedArray.getInt(R.styleable.SearchView_viewTheme, 1);
        boolean isAditable = typedArray.getBoolean(R.styleable.SearchView_editable, false);

        typedArray.recycle();


        String namespace = "http://schemas.android.com/apk/res/android";
        int viewHeight = attrs.getAttributeIntValue(namespace, "layout_height", ViewGroup.LayoutParams.MATCH_PARENT);
        int viewWidth = attrs.getAttributeIntValue(namespace, "layout_width", ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutParams layoutParams = new LayoutParams(viewWidth, viewHeight);

        final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_search_view, this, true);
        linearLayout.setLayoutParams(layoutParams);
        ImageView icon = linearLayout.findViewById(R.id.tip_icon);
        mText = linearLayout.findViewById(R.id.tip_text);


        //根据focusable设置是否
        if (!isAditable) {
            mText.setFocusable(false);
            mText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.performClick();
                }
            });
        }


        if (theme != 1) {
            icon.setImageResource(R.mipmap.icon_search);
            int color = getResources().getColor(R.color.sfy_black);
            mText.setTextColor(color);
            mText.setHintTextColor(color);
            linearLayout.setBackgroundResource(R.drawable.shape_round_rect_light_gray);
        }
    }

    public String getText() {
        return mText.getText().toString();
    }

    public void setText(String text) {
        mText.setText(text);
    }


    public void clearText() {
        mText.setText("");
    }



    public void enableEdit(boolean enable) {
        mText.setFocusable(enable);
    }

    public void addTextChanged(TextWatcher textWatcher) {
        mText.addTextChangedListener(textWatcher);
    }

    public void onKeyOk(final OnKeyOkListener listener) {
        mText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LogUtils.d(v.getText() + "   event:" + actionId);
                listener.onClick();
                KeyboardUtils.getInstance().hideKeyboard(mText);
                return true;
            }
        });
    }
}
