package com.bawei.day1220;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.bawei.day1220.view.activity.SecondActivity;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220
 * ClassName:   MyJavascript
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_13:16
 */
public class MyJavascriptInterface {
    private Context context;

    public MyJavascriptInterface(Context context) {

        this.context = context;
    }

    @JavascriptInterface                  //注解^_^
    public void getData() {
        Toast.makeText(context, "无参调用android方法", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface                  //注解^_^
    public void getData(String value) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }
}
