package com.bawei.day1220.utils;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.utils
 * ClassName:   My
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_14:36
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {//自定义类实现Thread.UncaughtExceptionHandler接口
    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {//重写其中的方法
        Log.e("xxx",thread.getName() +"=====：" +throwable);
    }
}
