package com.example.home_around.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.module_around.R;
import com.example.lib_generic.base.SfyBaseActivity;

public abstract class BaseTitleActivity extends SfyBaseActivity {

    protected ImageView mBack;
    protected TextView mTitle;
    protected TextView mOk;
    protected FrameLayout   mContent;

    @Override
    protected void initCommon() {
        super.initCommon();
        setContentView(R.layout.activity_title_base);
        mBack = findViewById(R.id.back);
        mTitle = findViewById(R.id.title);
        mOk = findViewById(R.id.ok);
        mContent = findViewById(R.id.content);

        if (0 == getContentViewId()) {
            throw new RuntimeException("布局文件呢");
        }
        View view = LayoutInflater.from(this).inflate(getContentViewId(), null, false);
        mContent.addView(view);

    }

    /**
     * 资源文件id
     * @return
     */
    abstract protected int getContentViewId();

    public void setBackVisible(boolean visible) {
        mBack.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setOkVisible(boolean visible) {
        mOk.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
