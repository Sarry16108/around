package com.example.home_around.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home_around.activity.SubmitOrderOfAppointActivity;
import com.example.home_around.adapter.GroupDataAdapter;
import com.example.home_around.adapter.GroupNameAdapter;
import com.example.home_around.entity.GroupData;
import com.example.home_around.entity.GroupNameData;
import com.example.home_around.utils.ActManager;
import com.example.home_around.widget.OnItemClickListener;
import com.example.home_around.widget.OnSimpleItemClickListener;
import com.example.lib_generic.base.SfyBaseActivity;
import com.example.lib_generic.base.SfyBaseFragment;
import com.example.lib_generic.utils.GlideUtils;
import com.example.lib_generic.utils.LogUtils;
import com.example.lib_generic.utils.ToastUtils;
import com.example.module_around.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsSelectFragment extends SfyBaseFragment {
    private RecyclerView    mGroupName;
    private RecyclerView    mDatas;

    private GroupNameAdapter    mGroupNameAdapter;
    private GroupDataAdapter    mGroupDataAdapter;

    //上次点击位置
    private int mOldPos = 0;
    //是否联动
    private boolean mScrollStick = false;

    private LinearLayoutManager datasLayoutManager;
    private LinearLayoutManager nameLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_select, container, false);
        initCommon(view);
        initData();

        return view;
    }


    private void initCommon(View view) {
        mGroupName = view.findViewById(R.id.group_name);
        mDatas = view.findViewById(R.id.datas);

        mGroupNameAdapter = new GroupNameAdapter(getContext());
        nameLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mGroupName.setLayoutManager(nameLayoutManager);
        mGroupName.setAdapter(mGroupNameAdapter);
        mGroupNameAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View preView, View view, int pos) {
                LogUtils.d(preView + "   view：" + view);

                //设置数据滚动到指定类型
                mOldPos = pos;
                mGroupDataAdapter.scrollToType(mGroupNameAdapter.getItemType(pos));
            }
        });
        mGroupNameAdapter.setDefaultSelectItem(0);

        mGroupDataAdapter = new GroupDataAdapter(getContext(), mDatas);
        datasLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mDatas.setLayoutManager(datasLayoutManager);
        mDatas.setAdapter(mGroupDataAdapter);
        mGroupDataAdapter.setOnItemClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                ActManager.toGoodsDetailOfActual((SfyBaseActivity) getActivity(), mGroupDataAdapter.getItem(pos).getId());
            }
        });
        mGroupDataAdapter.setOnAddClickListener(new OnSimpleItemClickListener() {
            @Override
            public void OnClick(View view, int pos) {
                LogUtils.d("选择的位置 " + pos);
                GroupData.GroupItemData item = mGroupDataAdapter.getItem(pos);
                goodsTypesSelect(item.getImgUrl(), item.getTitle(), item.getPrice());
            }
        });


        mDatas.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtils.d("newState：" + newState);
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    mScrollStick = true;
                }

                if (RecyclerView.SCROLL_STATE_IDLE == newState && mScrollStick) {
                    mScrollStick = false;
                }
            }
        });
        //滚动联动设置
        mDatas.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (!mScrollStick) {
                    return;
                }

                int pos = datasLayoutManager.findFirstVisibleItemPosition();
                LogUtils.d("pos：" + pos + "  old pos：" + mOldPos);
                if (mOldPos != pos) {
                    GroupData.GroupItemData item = mGroupDataAdapter.getItem(pos);
                    nameLayoutManager.scrollToPosition(item.getType());
                    mGroupNameAdapter.setItemSelectByPos(item.getType());
                    mOldPos = pos;
                }
            }
        });
    }


    private void initData() {
        List<GroupNameData> list = new ArrayList<>();
        list.add(new GroupNameData(0, "星级咖啡"));
        list.add(new GroupNameData(1, "茶瓦纳"));
        list.add(new GroupNameData(2, "手工咖啡"));
        list.add(new GroupNameData(3, "星冰乐"));
        list.add(new GroupNameData(4, "茶系列"));
        list.add(new GroupNameData(5, "大师咖啡"));
        mGroupNameAdapter.setItems(list);


        List<GroupData.GroupItemData> datas = new ArrayList<>();
        datas.add(new GroupData.GroupItemData(0, 0, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(1, 0, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(2, 0, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(3, 0, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(4, 1, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(5, 1, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(6, 1, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(7, 2, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(8, 2, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(9, 2, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(10, 2, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(11, 3, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(12, 3, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(13, 3, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(14, 4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(15, 4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(16, 4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(17, 4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(18, 4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(19, 4, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(20, 5, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(21, 5, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(22, 5, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        datas.add(new GroupData.GroupItemData(23, 5, "http://192.168.1.23/resource-file/2018-12-10/2018-12-10-e7b8caf6-00cb-4581-9968-d0643480450c.jpg", "哎呀呀呀呀", "扩展信息都有啥可显示的", "15"));
        mGroupDataAdapter.setItems(datas);

    }


    Dialog mDialog;
    ImageView close;
    TextView extInfo;
    TextView cupSmall;
    TextView cupMiddle;
    TextView cupBig;
    TextView cupBigger;
    TextView hot;
    TextView warm;
    TextView cool;
    TextView iceCool;
    TextView milk;
    TextView sugger;
    TextView ok;

    private void goodsTypesSelect(String imgUrl, String ti, String pri) {
        mDialog = new Dialog(getContext(), R.style.AppNoActionBarWidthFull);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_goods_types_selector);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity =Gravity.BOTTOM;
        window.setAttributes(layoutParams);


        close = mDialog.findViewById(R.id.close);
        ImageView image = mDialog.findViewById(R.id.image);
        TextView title = mDialog.findViewById(R.id.title);
        extInfo = mDialog.findViewById(R.id.ext_info);
        TextView price = mDialog.findViewById(R.id.price);
        cupSmall = mDialog.findViewById(R.id.cup_small);
        cupMiddle = mDialog.findViewById(R.id.cup_middle);
        cupBig = mDialog.findViewById(R.id.cup_big);
        cupBigger = mDialog.findViewById(R.id.cup_bigger);
        hot = mDialog.findViewById(R.id.hot);
        warm = mDialog.findViewById(R.id.warm);
        cool = mDialog.findViewById(R.id.cool);
        iceCool = mDialog.findViewById(R.id.ice_cool);
        milk = mDialog.findViewById(R.id.milk);
        sugger = mDialog.findViewById(R.id.sugger);
        ok = mDialog.findViewById(R.id.settlement);

        close.setOnClickListener(clickListener);
        cupSmall.setOnClickListener(clickListener);
        cupMiddle.setOnClickListener(clickListener);
        cupBig.setOnClickListener(clickListener);
        cupBigger.setOnClickListener(clickListener);
        hot.setOnClickListener(clickListener);
        warm.setOnClickListener(clickListener);
        cool.setOnClickListener(clickListener);
        iceCool.setOnClickListener(clickListener);
        milk.setOnClickListener(clickListener);
        sugger.setOnClickListener(clickListener);
        ok.setOnClickListener(clickListener);

        GlideUtils.showImage(image, imgUrl);
        title.setText(ti);
        extInfo.setText("已选：");
        price.setText(pri);

        mDialog.show();
    }

    private String selectCup = "";
    private String selectTemp = "";
    private String selectAddi = "";

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.close:
                    mDialog.dismiss();
                    break;
                case R.id.cup_small:
                    selectCup = "小杯";
                    cupSmall.setSelected(true);
                    cupMiddle.setSelected(false);
                    cupBig.setSelected(false);
                    cupBigger.setSelected(false);
                    break;
                case R.id.cup_middle:
                    selectCup = "中杯";
                    cupSmall.setSelected(false);
                    cupMiddle.setSelected(true);
                    cupBig.setSelected(false);
                    cupBigger.setSelected(false);
                    break;
                case R.id.cup_big:
                    selectCup = "大杯";
                    cupSmall.setSelected(false);
                    cupMiddle.setSelected(false);
                    cupBig.setSelected(true);
                    cupBigger.setSelected(false);
                    break;
                case R.id.cup_bigger:
                    selectCup = "超大杯";
                    cupSmall.setSelected(false);
                    cupMiddle.setSelected(false);
                    cupBig.setSelected(false);
                    cupBigger.setSelected(true);
                    break;
                case R.id.hot:
                    selectTemp = "热";
                    hot.setSelected(true);
                    warm.setSelected(false);
                    cool.setSelected(false);
                    iceCool.setSelected(false);
                    break;
                case R.id.warm:
                    selectTemp = "常温";
                    hot.setSelected(false);
                    warm.setSelected(true);
                    cool.setSelected(false);
                    iceCool.setSelected(false);
                    break;
                case R.id.cool:
                    selectTemp = "去冰";
                    hot.setSelected(false);
                    warm.setSelected(false);
                    cool.setSelected(true);
                    iceCool.setSelected(false);
                    break;
                case R.id.ice_cool:
                    selectTemp = "冰";
                    hot.setSelected(false);
                    warm.setSelected(false);
                    cool.setSelected(false);
                    iceCool.setSelected(true);
                    break;
                case R.id.milk:
                    selectAddi = "奶油";
                    milk.setSelected(true);
                    sugger.setSelected(false);
                    break;
                case R.id.sugger:
                    selectAddi = "风味糖浆";
                    milk.setSelected(false);
                    sugger.setSelected(true);
                    break;
                case R.id.settlement:
                    mDialog.dismiss();
                    return;
            }

            extInfo.setText("已选：" + selectCup + "/" + selectTemp + "/" + selectAddi);
        }
    };
}
