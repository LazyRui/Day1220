package com.bawei.day1220.presenter;

import com.bawei.day1220.base.mvp.BasePresenter;
import com.bawei.day1220.contract.ProductContract;
import com.bawei.day1220.model.ProductModel;

import java.util.Map;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.presenter
 * ClassName:   ProductPresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:59
 */
public class ProductPresenter extends BasePresenter<ProductModel, ProductContract.IView> implements ProductContract.IPresenter {
    @Override
    protected ProductModel initModel() {
        return new ProductModel();
    }

    @Override
    public void setProductEntityData(String url, Map<String, String> params) {
        model.getProductEntityData(url, params, new ProductContract.IModel.OnDataCallBack() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    getView().success(o);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (throwable != null) {
                    getView().failure(throwable);
                }
            }
        });

    }

    @Override
    public void getProductHistoryEntityData(String url) {
        model.getProductHistoryEntityData(url, new ProductContract.IModel.OnDataCallBack() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    getView().success(o);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (throwable != null) {
                    getView().failure(throwable);
                }
            }
        });
    }
}
