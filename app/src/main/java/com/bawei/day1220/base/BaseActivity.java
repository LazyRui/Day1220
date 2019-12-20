package com.bawei.day1220.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.day1220.base.mvp.BasePresenter;
import com.bawei.day1220.base.mvp.IBaseView;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.base
 * ClassName:   BaseActivity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_9:58
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;//声明P层对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());//layoutId()：加载根布局
        presenter = initPresenter();//初始化P层对象
        if (presenter != null) {
            presenter.attach(this);//绑定V层对象
        }
        initView();//找控件
        initData();//初始化数据
    }

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutId();

    /**
     * 当前activity销毁时调用解绑方法处理内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
