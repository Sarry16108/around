package com.example.home_around.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib_generic.base.SfyBaseFragment;
import com.example.module_around.R;

public class GoodsEvaluationFragment extends SfyBaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_evaluation, container, false);
        initCommon(view);
        initData();

        return view;
    }


    private void initCommon(View view) {

    }


    private void initData() {

    }
}
