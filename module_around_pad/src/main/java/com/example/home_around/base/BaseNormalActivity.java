package com.example.home_around.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_generic.base.SfyBaseActivity;

public abstract class BaseNormalActivity extends SfyBaseActivity {

    @Override
    protected void initCommon() {
        super.initCommon();

        View view = LayoutInflater.from(this).inflate(getContentViewId(), null, false);
        setContentView(view);

        if (0 == getContentViewId()) {
            throw new RuntimeException("布局文件呢");
        }


    }

    /**
     * 资源文件id
     * @return
     */
    abstract protected int getContentViewId();

}
