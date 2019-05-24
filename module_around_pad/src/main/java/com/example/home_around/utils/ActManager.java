package com.example.home_around.utils;

import android.content.Intent;

import com.example.home_around.activity.GoodsDetailOfActualActivity;
import com.example.home_around.activity.GoodsDetailOfAppointActivity;
import com.example.home_around.activity.GoodsDetailOfServiceActivity;
import com.example.home_around.activity.MainActivity;
import com.example.home_around.activity.MerchantDetailOfActualActivity;
import com.example.home_around.activity.MerchantDetailOfServiceActivity;
import com.example.home_around.activity.MerchantsActivity;
import com.example.home_around.activity.MyOrderActivity;
import com.example.home_around.activity.OrderDetailActivity;
import com.example.home_around.activity.PayResultActivity;
import com.example.home_around.activity.SearchActivity;
import com.example.home_around.activity.SelectAddressActivity;
import com.example.home_around.activity.ShoppingCartActivity;
import com.example.home_around.activity.SubmitOrderOfAppointActivity;
import com.example.home_around.activity.SubmitOrderOfDeliveryActivity;
import com.example.home_around.activity.SubmitOrderOfServiceActivity;
import com.example.home_around.base.BaseWebActivity;
import com.example.lib_generic.base.SfyBaseActivity;

/**
 * 页面管理类
 */
public class ActManager {



    /***************************************************************************
     *                                      activity跳转
     *
     ***************************************************************************/
    /**
     *  首页
     */
    public static void toMain(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    /**
     *  首页
     */
    public static void toWeb(SfyBaseActivity activity, String url) {
        Intent intent = new Intent(activity, BaseWebActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    /**
     *  搜索页
     */
    public static void toSearch(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }

    /**
     *  购物车
     */
    public static void toShoppingCart(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, ShoppingCartActivity.class);
        activity.startActivity(intent);
    }

    /**
     *  实物详情
     */
    public static void toGoodsDetailOfActual(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, GoodsDetailOfActualActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    /**
     *  服务类详情
     */
    public static void toGoodsDetailOfService(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, GoodsDetailOfServiceActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    /**
     *  预定类详情
     */
    public static void toGoodsDetailOfAppoint(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, GoodsDetailOfAppointActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    /**
     *  商家实物类
     */
    public static void toMerchantDetailOfActual(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, MerchantDetailOfActualActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    /**
     *  商家服务类详情
     */
    public static void toMerchantDetailOfService(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, MerchantDetailOfServiceActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    /**
     *  商家列表
     */
    public static void toMerchants(SfyBaseActivity activity, int type) {
        Intent intent = new Intent(activity, MerchantsActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    /**
     *  服务类订单确认页
     */
    public static void toSubmitOrderOfService(SfyBaseActivity activity, int type) {
        Intent intent = new Intent(activity, SubmitOrderOfServiceActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    /**
     *  预定类订单确认页
     */
    public static void toSubmitOrderOfAppoint(SfyBaseActivity activity, int type) {
        Intent intent = new Intent(activity, SubmitOrderOfAppointActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    /**
     *  选择地址
     */
    public static void toSelectAddress(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, SelectAddressActivity.class);
        activity.startActivity(intent);
    }

    /**
     *  配送类页面
     */
    public static void toSubmitOrderOfDelivery(SfyBaseActivity activity) {
        Intent intent = new Intent(activity, SubmitOrderOfDeliveryActivity.class);
        activity.startActivity(intent);
    }

    /**
     *  详情
     */
    public static void toOrderDetail(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    /**
     * 我的订单
     */
    public static void toMyOrder(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, MyOrderActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    /**
     * 我的订单
     */
    public static void toPayResult(SfyBaseActivity activity, int id) {
        Intent intent = new Intent(activity, PayResultActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

}
