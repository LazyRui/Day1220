package com.bawei.day1220.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bawei.day1220.R;
import com.bawei.day1220.base.BaseFragment;
import com.bawei.day1220.base.mvp.BasePresenter;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.view.fragment
 * ClassName:   HomeFragment
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:18
 */
public class OtherFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected void initData() {
        String key = getArguments().getString("key");
        textView.setText(key);

    }

    @Override
    protected void initView(View view) {
        textView = view.findViewById(R.id.tv);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    /**
     * 使用静态传参
     * @param value
     * @return
     */
    public static OtherFragment getInstance(String value) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",value);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
