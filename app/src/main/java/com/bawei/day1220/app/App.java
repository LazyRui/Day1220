package com.bawei.day1220.app;

import android.app.Application;
import android.content.Context;

import com.bawei.day1220.utils.MyUncaughtExceptionHandler;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.app
 * ClassName:   App
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:03
 */
public class App extends Application {
    private static Context context;//声明Context

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();//初始化context
        //创建全局异常类
        MyUncaughtExceptionHandler uncaughtExceptionHandler = new MyUncaughtExceptionHandler();
        //设置DefaultUncaughtExceptionHandler
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);

    }

    //提供静态方法
    public static Context getContext() {
        return context;
    }
}
