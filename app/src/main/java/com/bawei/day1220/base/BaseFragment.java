package com.bawei.day1220.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.day1220.base.mvp.BasePresenter;
import com.bawei.day1220.base.mvp.IBaseView;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.base
 * ClassName:   BaseFragment
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:01
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected P presenter;//声明P层对象

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), layoutId(), null);//layoutId()：加载根布局  绑定根布局
        presenter = initPresenter();//初始化P层对象
        if (presenter != null) {
            presenter.attach(this);//绑定V层对象
        }
        initView(view);//找控件
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//初始化数据
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract P initPresenter();

    protected abstract int layoutId();

    /**
     * 当前activity销毁时调用解绑方法处理内存泄漏
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
