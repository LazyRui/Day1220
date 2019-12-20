package com.bawei.day1220.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day1220.R;
import com.bawei.day1220.model.entity.ProductEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.view.adapter
 * ClassName:   MyRecyclerViewAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_11:22
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.VH> {


    private final Context context; //声明上下文
    private final List<ProductEntity.ResultBean> result;//声明集合

    /**
     * 当前类的有参构造，在构造中初始化数据并赋值
     * @param context
     * @param result
     */
    public MyRecyclerViewAdapter(Context context, List<ProductEntity.ResultBean> result) {

        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_resyclerview,null);//绑定布局
        VH vh = new VH(view);//创建vh对象并返回
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final ProductEntity.ResultBean list = result.get(position);//得到当前position所对应的对象
        Glide.with(context)
                .load(list.getMasterPic())//加载图片网址接口
                .placeholder(R.mipmap.ic_launcher)//占位图
                .error(R.mipmap.ic_launcher)//错误图
                .apply(new RequestOptions().transform(new RoundedCorners(12)))//加载圆角图
                .into(holder.imageView);//加载完成后使用into给到当前的imageView控件

        holder.textView1.setText(list.getCommodityName());//给对应的文本控件赋值
        holder.textView2.setText("￥:" + list.getPrice());//给对应的文本控件赋值

        /**
         * 点击事件
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onProductItemClickListener != null) {//通过自定义接口欧回调数据（）：括号内的数据根据使用的场景不同参数也不同
                    onProductItemClickListener.ClickListener(list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    /**
     * 自定义一个类继承RecyclerView.ViewHolder
     *      并实现其中的方法，在方法中声明对象并找控件
     */
    public class VH extends RecyclerView.ViewHolder{
        private ImageView imageView;//声明ImageView
        private TextView textView1,textView2;//声明TextView
        public VH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);//找控件初始化ImageView
            textView1 = itemView.findViewById(R.id.tv1);//找控件初始化TextView
            textView2 = itemView.findViewById(R.id.tv2);//找控件初始化TextView
        }
    }


    /**
     * 接口回调三步骤
     */

    //第二步：声明接口对象
    private OnProductItemClickListener onProductItemClickListener;
    //第三步：提供对外使用的方法setOnProductItemClickListener：通常set就可以直接出来
    public void setOnProductItemClickListener(OnProductItemClickListener onProductItemClickListener) {
        this.onProductItemClickListener = onProductItemClickListener;
    }

    //第一步：声明接口类
    public interface OnProductItemClickListener{
        void ClickListener(ProductEntity.ResultBean resultBean);//通过自定义接口欧回调数据（）：括号内的数据根据使用的场景不同参数也不同   根据需求而定
    }







}
