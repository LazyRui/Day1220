package com.bawei.day1220.weight;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.day1220.R;

import java.util.List;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.weight
 * ClassName:   FlowView
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:36
 */
public class FlowView extends ViewGroup {

    private int bottom;

    public FlowView(Context context) {
        this(context, null);
    }

    public FlowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int left = 0;
        int top = 0;
        int right = 0;
        bottom = 0;

        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View view = getChildAt(i4);

                view.measure(0, 0);

                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();

                right = left + width;
                if (right > getResources().getDisplayMetrics().widthPixels) {
                    left = 0;
                    right = left + width;
                    top = bottom + 20;
                }

                bottom = top + height;

                view.layout(left, top, right, bottom);

                left += width + 20;

            }
        }

    }

    /**
     *  条目点击仅供参考，用自己会的方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {//判断按下操作
            int x = (int) event.getX();//获取按下时的x坐标
            int y = (int) event.getY();//获取按下时的y坐标

            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);//遍历集合获取对象
                Rect rect = new Rect();//创建矩形
                view.getHitRect(rect);//获取view在屏幕中的位置
                if (rect.contains(x, y)) {//判断当前的矩形是否包含按下时的(x,y)坐标
                    if (onItemClickListener != null) {
                        onItemClickListener.clickListener(view, i);//回调对应
                        view.performClick();//代替手动调用点击事件
                    }
                }
            }


        }


        return true;
    }

    /**
     * 接口回调三步骤
     */

    //第二步：声明接口对象
    private OnItemClickListener onItemClickListener;

    //第三步：提供对外使用的方法setOnProductItemClickListener：通常set就可以直接出来
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //第一步：声明接口类
    public interface OnItemClickListener {
        void clickListener(View view, int position);//通过自定义接口欧回调数据（）：括号内的数据根据使用的场景不同参数也不同   根据需求而定
    }

    /**
     * 添加子view的方法
     * @param tags  集合对象
     */
    public void addChildView(List<String> tags) {
        if (tags != null) {
            for (String value : tags) {//使用foreach遍历集合

                TextView textView = new TextView(getContext());//创建文本对象
                textView.setGravity(Gravity.CENTER);//设置居中属性
                textView.setPadding(10,0,10,0);//设置内填充
                textView.setBackgroundResource(R.drawable.tv_bg);//设置背景图片
                textView.setText(value);//设置内容

                addView(textView);//添加到子view
            }
        }
        /**
         * 以下内容仅供参考
         */
        if (tags.size() == 0) {
            TextView textView = new TextView(getContext());

            textView.setText("暂无此历史记录");

            addView(textView);
        }
    }


}
