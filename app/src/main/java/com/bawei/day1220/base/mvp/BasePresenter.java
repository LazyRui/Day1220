package com.bawei.day1220.base.mvp;

import java.lang.ref.WeakReference;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.base.mvp
 * ClassName:   BasePresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_9:56
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {

    protected M model;//声明M层对象
    private WeakReference<V> weakReference;//声明弱引用对象

    public BasePresenter() {

        model = initModel();//初始化M层：因为不确定子类对象，所以让子类初始化

    }

    /**
     * 绑定：绑定V层对象
     * @param v
     */
    public void attach(V v) {
        weakReference = new WeakReference<>(v);//初始化weakReference
    }

    protected abstract M initModel();

    /**
     * 解绑：使用弱引用防止内存泄漏
     */
    public void detach() {
        if (weakReference != null) {
            weakReference.clear();//清空weakReference中的对象
            weakReference = null;//weakReference对象置空
        }
    }

    /**
     * 返回V层对象
     * @return
     */
    public V getView() {
        return weakReference.get();//通过weakReference.get()方法得到V层对象
    }
}
