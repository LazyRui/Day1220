package com.bawei.day1220.model;

import com.bawei.day1220.contract.ProductContract;
import com.bawei.day1220.model.entity.ProductEntity;
import com.bawei.day1220.model.entity.ProductHistoryEntity;
import com.bawei.day1220.utils.VolleyUtils;
import com.google.gson.Gson;

import java.util.Map;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.model
 * ClassName:   ProductModel
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:51
 */
public class ProductModel implements ProductContract.IModel {

    /**
     * M层处理商品信息方法
     * @param url
     * @param params
     * @param dataCallBack
     */
    @Override
    public void getProductEntityData(String url, Map<String, String> params, final OnDataCallBack dataCallBack) {
        VolleyUtils.getInstance().doGet(url, params, new VolleyUtils.VolleyCallBack() {
            @Override
            public void success(String json) {
                if (dataCallBack != null) {
                    ProductEntity productEntity = new Gson().fromJson(json, ProductEntity.class);//解析商品信息json
                    dataCallBack.success(productEntity);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (dataCallBack != null) {
                    dataCallBack.failure(throwable);
                }
            }
        });
    }

    /**
     * M层处理历史搜索信息方法
     * @param url
     * @param dataCallBack
     */
    @Override
    public void getProductHistoryEntityData(String url,final OnDataCallBack dataCallBack) {
        VolleyUtils.getInstance().doGet(url, null, new VolleyUtils.VolleyCallBack() {
            @Override
            public void success(String json) {
                if (dataCallBack != null) {
                    ProductHistoryEntity productHistoryEntity = new Gson().fromJson(json, ProductHistoryEntity.class);//解析历史搜索信息json
                    dataCallBack.success(productHistoryEntity);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (dataCallBack != null) {
                    dataCallBack.failure(throwable);
                }
            }
        });
    }
}
