package com.example.lib_generic.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lib_generic.R;

public class GlideUtils {
    private static RequestOptions  centerCrop = new RequestOptions().circleCrop().placeholder(R.mipmap.icon_app_portrait);

    public static void showCenterCrop(ImageView view, String url) {
        Glide.with(view.getContext()).applyDefaultRequestOptions(centerCrop).load(url).into(view);
    }

    public static void showImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
