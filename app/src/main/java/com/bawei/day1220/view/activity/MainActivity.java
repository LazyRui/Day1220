package com.bawei.day1220.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.bawei.day1220.R;
import com.bawei.day1220.base.BaseActivity;
import com.bawei.day1220.base.mvp.BasePresenter;
import com.bawei.day1220.view.adapter.MyFragmentPagerAdapter;
import com.bawei.day1220.view.fragment.HomeFragment;
import com.bawei.day1220.view.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<Fragment> list;//声明list：作为vp加载fragment的对象集合
    private ViewPager viewPager;//声明vp
    private RadioGroup radioGroup;//声明rg

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        list = new ArrayList<>();//初始化集合

        HomeFragment homeFragment = new HomeFragment();//创建fragment
        OtherFragment beijing = OtherFragment.getInstance("北京");//fragment创建+fragment的动态传值
        OtherFragment mine = OtherFragment.getInstance("我的");
        OtherFragment news = OtherFragment.getInstance("新闻");

        list.add(homeFragment);//添加到集合中
        list.add(beijing);//添加到集合中
        list.add(mine);//添加到集合中
        list.add(news);//添加到集合中

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list);//创建适配器MyFragmentPagerAdapter：使用构造方法传参

        viewPager.setAdapter(myFragmentPagerAdapter);//设置适配器


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//addOnPageChangeListener：vp的page改变监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {//页面选中：
                radioGroup.check(radioGroup.getChildAt(position).getId());//根据选中的当前vp的position来作为rg的子控件rb的选中，达到一个联动的效果
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {//根据选中的当前rg的i来作为vp的当前page选中，达到一个联动的效果
                switch (i) {
                    case R.id.rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });

        viewPager.setOffscreenPageLimit(4);//这个方法不需要 (*^▽^*) ~
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.vp);//找控件初始化ViewPager
        radioGroup = findViewById(R.id.rg);//找控件初始化RadioGroup

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;//加载根布局
    }
}
