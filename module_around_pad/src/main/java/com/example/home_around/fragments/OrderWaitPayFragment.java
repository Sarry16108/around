package com.example.home_around.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.home_around.adapter.MerchantServiceAdapter;
import com.example.home_around.adapter.MyOrderAdapter;
import com.example.home_around.entity.MerchantServiceData;
import com.example.home_around.entity.MyOrderData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.base.SfyBaseFragment;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class OrderWaitPayFragment extends SfyBaseFragment {
    private RecyclerView    mDatas;

    private MyOrderAdapter mMyOrderAdapter;

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

        mMyOrderAdapter = new MyOrderAdapter(getContext());
        mDatas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mDatas.setAdapter(mMyOrderAdapter);
        mMyOrderAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                MyOrderData data = mMyOrderAdapter.getItem(pos);

                switch (data.getType()) {
                    case 0:
//                        state.setText("等待付款");
                        if (view.getTag() == "1") {
                            ToastUtils.shortToast(getActivity(), "取消订单");
                        } else {
                            ToastUtils.shortToast(getActivity(), "立即付款");
                        }
                        break;
                }
            }
        });
    }


    private void initData() {

        List<MyOrderData> datas = new ArrayList<>();
        datas.add(new MyOrderData("耐克专卖店","http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "18", "18",2, 0));
        datas.add(new MyOrderData("星巴克旗舰店（虹梅路店）","http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "33","18", 1, 0));
        datas.add(new MyOrderData("玩呗城堡（爱琴海店）", "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "25", "18",3, 0));
        datas.add(new MyOrderData("科瓦齿科（金茂大厦店）", "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "21", "18",4, 0));
        datas.add(new MyOrderData("耐克专卖店","http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "55", "18",2, 0));
        datas.add(new MyOrderData("星巴克旗舰店（虹梅路店）","http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "234", "18",1, 0));
        datas.add(new MyOrderData("玩呗城堡（爱琴海店）","http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "12", "18",2, 0));
        datas.add(new MyOrderData("科瓦齿科（金茂大厦店）","http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "221", "18",3, 0));
        datas.add(new MyOrderData("星巴克旗舰店（虹梅路店）", "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "342", "18",1, 0));
        mMyOrderAdapter.setItems(datas);

    }
}
