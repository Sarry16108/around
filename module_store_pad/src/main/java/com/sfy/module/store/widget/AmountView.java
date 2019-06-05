package com.sfy.module.store.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sfy.module.store.R;


public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goodsStorage = 1; //商品库存

    private OnAmountChangeListener mListener;

    private EditText mAmount;
    private TextView mDecrease;
    private TextView mIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        mAmount = findViewById(R.id.amount);
        mDecrease = findViewById(R.id.decrease);
        mIncrease = findViewById(R.id.increase);
        mDecrease.setOnClickListener(this);
        mIncrease.setOnClickListener(this);
        mAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btn_width, LayoutParams.WRAP_CONTENT);
        int numWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_num_width, 0);
        int numTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_num_text_size, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btn_text_size, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.AmountView_text_color, 0);
        obtainStyledAttributes.recycle();

        if (btnWidth > 0) {
            LayoutParams textParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
            mDecrease.setLayoutParams(textParams);
            mIncrease.setLayoutParams(textParams);
        }

        if (btnTextSize > 0) {
            mDecrease.setTextSize(btnTextSize);
            mIncrease.setTextSize(btnTextSize);
        }

        if (numWidth > 0) {
            LayoutParams textParams = new LayoutParams(numWidth, LayoutParams.MATCH_PARENT);
            mAmount.setLayoutParams(textParams);
        }

        if (numTextSize > 0) {
            mAmount.setTextSize(numTextSize);
        }

        if (color > 0) {
            mIncrease.setTextColor(color);
            mDecrease.setTextColor(color);
            mAmount.setTextColor(color);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoodsStorage(int goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public void setCount(int count) {
        this.amount = count;
        this.mAmount.setText("" + count);
    }

    public int getCount() {
        return this.amount;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.decrease) {
            if (amount > 1) {
                amount--;
                mAmount.setText(amount + "");
            }
        } else if (i == R.id.increase) {
            if (amount < goodsStorage) {
                amount++;
                mAmount.setText(amount + "");
            }
        }

        mAmount.clearFocus();

//        if (mListener != null) {
//            mListener.onAmountChange(this, amount);
//        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goodsStorage) {
            mAmount.setText(goodsStorage + "");
            return;
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
