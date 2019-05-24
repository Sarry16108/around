package com.example.home_around.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home_around.adapter.GroupDataAdapter;
import com.example.home_around.adapter.GroupNameAdapter;
import com.example.home_around.adapter.MerchantServiceAdapter;
import com.example.home_around.entity.GroupData;
import com.example.home_around.entity.GroupNameData;
import com.example.home_around.entity.MerchantServiceData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.base.SfyBaseFragment;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class MerchantSelectFragment extends SfyBaseFragment {
    private RecyclerView    mDatas;

    private MerchantServiceAdapter mMerchantServiceAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_merchant_select, container, false);
        initCommon(view);
        initData();

        return view;
    }


    private void initCommon(View view) {
        mDatas = view.findViewById(R.id.datas);

        mMerchantServiceAdapter = new MerchantServiceAdapter(getContext(), mDatas);
        mDatas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mDatas.setAdapter(mMerchantServiceAdapter);
        mMerchantServiceAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                ActManager.toGoodsDetailOfService((SfyBaseActivity) getActivity(), mMerchantServiceAdapter.getItem(pos).getId());
            }
        });
        mMerchantServiceAdapter.setOnAddClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                ToastUtils.shortToast(getContext(), "选择的位置 " + pos);
            }
        });
    }


    private void initData() {

        List<MerchantServiceData.MerchantServiceItemData> datas = new ArrayList<>();
        datas.add(new MerchantServiceData.MerchantServiceItemData(0, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(1, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(2, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(3, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(5, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(6, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(7, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(8, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(9, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(10, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(11, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(12, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(13, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(14, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(15, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(16, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(17, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(18, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(19, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(20, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(21, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(22, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new MerchantServiceData.MerchantServiceItemData(23, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        mMerchantServiceAdapter.setItems(datas);

    }
}
