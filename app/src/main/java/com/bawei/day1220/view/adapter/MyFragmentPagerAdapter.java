package com.bawei.day1220.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.view.adapter
 * ClassName:   MyFragmentPagerAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:25
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> list;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);//返回当前position对应的fragment
    }

    @Override
    public int getCount() {
        return list.size();//返回fragment的总数
    }
}
