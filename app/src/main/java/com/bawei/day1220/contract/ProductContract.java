package com.bawei.day1220.contract;

import com.bawei.day1220.base.mvp.IBaseModel;
import com.bawei.day1220.base.mvp.IBaseView;

import java.util.Map;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.contract
 * ClassName:   ProductContract
 * Description: 契约类统一管理
 * Author: Lazy
 * CreateDate: 2019/12/20_10:46
 */
public class ProductContract {

    public interface IModel extends IBaseModel {//声明M层接口

        /**
         *
         * @param url
         * @param params
         * @param dataCallBack
         */
        void getProductEntityData(String url, Map<String, String> params, OnDataCallBack dataCallBack);

        /**
         *
         * @param url
         * @param dataCallBack
         */
        void getProductHistoryEntityData(String url,OnDataCallBack dataCallBack);

        interface OnDataCallBack {//接口回调：M层数据处理完成后回调数据
            void success(Object o);

            void failure(Throwable throwable);
        }
    }

    public interface IView extends IBaseView {//声明V层接口

        /**
         *
         * @param o
         */
        void success(Object o);

        /**
         *
         * @param throwable
         */
        void failure(Throwable throwable);
    }

    public interface IPresenter {//声明P层接口

        /**
         * 得到商品的url接口+入参集合
         * @param url   商品类的接口
         * @param params  入参集合
         */
        void setProductEntityData(String url, Map<String, String> params);

        /**
         * 得到商品历史记录的url接口
         * @param url   商品历史搜索的接口
         */
        void getProductHistoryEntityData(String url);
    }
}
