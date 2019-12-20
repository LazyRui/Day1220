package com.bawei.day1220.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day1220.R;
import com.bawei.day1220.base.BaseFragment;
import com.bawei.day1220.base.mvp.BasePresenter;
import com.bawei.day1220.contract.ProductContract;
import com.bawei.day1220.model.entity.ProductEntity;
import com.bawei.day1220.model.entity.ProductHistoryEntity;
import com.bawei.day1220.presenter.ProductPresenter;
import com.bawei.day1220.view.activity.MainActivity;
import com.bawei.day1220.view.activity.SecondActivity;
import com.bawei.day1220.view.adapter.MyRecyclerViewAdapter;
import com.bawei.day1220.weight.FlowView;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.view.fragment
 * ClassName:   HomeFragment
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:18
 */
public class HomeFragment extends BaseFragment<ProductPresenter> implements ProductContract.IView {
    private Map<String, String> params;//声明Map集合
    private Button button;//声明button
    private EditText editText;//声明EditText
    private FlowView flowView;//声明flowView
    private RecyclerView recyclerView;//声明recyclerview

    @Override
    protected void initData() {


    }

    @Override
    protected void initView(View view) {
        button = view.findViewById(R.id.but);//找控件初始化button
        editText = view.findViewById(R.id.et);//找控件初始化EditText
        flowView = view.findViewById(R.id.flow);//找控件初始化flowView
        recyclerView = view.findViewById(R.id.rv);//找控件初始化recyclerview
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));//设置布局管理器：网格布局

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();//得到EditText输入的内容
                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(getContext(), "输入内容不能为空……", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    flowView.removeAllViews();//可以不用
                    params = new HashMap<>();//初始化集合
                    params.put("keyword", value);//添加数据
                    params.put("page", "1");//添加数据
                    params.put("count", "50");//添加数据
                    presenter.setProductEntityData("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword", params);//调用setProductEntityData方法获取数据
                    presenter.getProductHistoryEntityData("http://blog.zhaoliang5156.cn/baweiapi/" + URLEncoder.encode(value));//调用getProductHistoryEntityData方法获取数据
                }
            }
        });

        /**
         * 条目点击回调接口
         */
        flowView.setOnItemClickListener(new FlowView.OnItemClickListener() {
            @Override
            public void clickListener(View view, int position) {
                TextView textView = (TextView) view;//得到当前点击的条目对象
                String s = textView.getText().toString();//获取当前的内容
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                params = new HashMap<>();//初始化集合
                params.put("keyword", s);//添加数据
                params.put("page", "1");//添加数据
                params.put("count", "50");//添加数据
                presenter.setProductEntityData("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword", params);//调用setProductEntityData方法获取数据
            }
        });


    }

    @Override
    protected ProductPresenter initPresenter() {
        return new ProductPresenter();//初始化P层对象
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;//加载根布局
    }

    @Override
    public void success(Object o) {
        if (o != null) {//判空
            if (o instanceof ProductEntity) {//判断该左边对象是否是右边类对象的实例
                final List<ProductEntity.ResultBean> result = ((ProductEntity) o).getResult();//得到该类集合数据
                int size = result.size();
                Log.e("xxx", "===============：" + size);
                if (size == 0) {//可以不写
                    Toast.makeText(getContext(), "暂无此商品信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), result);//创建recyclerview适配器

                recyclerView.setAdapter(myRecyclerViewAdapter);//设置recyclerview适配器

                myRecyclerViewAdapter.setOnProductItemClickListener(new MyRecyclerViewAdapter.OnProductItemClickListener() {//自定义接口回调：点击对应商品条目并返回
                    @Override
                    public void ClickListener(ProductEntity.ResultBean resultBean) {
                        Toast.makeText(getContext(), resultBean.getCommodityName(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), SecondActivity.class);//创建intent，跳转对应activity
                        startActivity(intent);//点击跳转：
                    }
                });

            } else if (o instanceof ProductHistoryEntity) {//判断该左边对象是否是右边类对象的实例
                List<String> tags = ((ProductHistoryEntity) o).getTags();//获取该对象
                Log.e("xxx", "===============：" + tags.size());
                flowView.addChildView(tags);//添加子view

            }
        }
    }

    @Override
    public void failure(Throwable throwable) {
        Toast.makeText(getActivity(), ""+throwable, Toast.LENGTH_SHORT).show();//失败的toast
    }
}
