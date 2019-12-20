package com.bawei.day1220.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.day1220.app.App;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.Map;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.utils
 * ClassName:   VolleyUtils
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:05
 */
public class VolleyUtils {
    private static VolleyUtils volleyUtils;//声明静态工具类
    private RequestQueue requestQueue;

    private VolleyUtils() {
        requestQueue = Volley.newRequestQueue(App.getContext());//初始化requestQueue对象
    }

    /**
     * 使用双重校验锁
     * @return
     */
    public static VolleyUtils getInstance() {
        if (volleyUtils == null) {
            synchronized (VolleyUtils.class) {
                if (volleyUtils == null) {
                    volleyUtils = new VolleyUtils();//初始化工具类
                }
            }
        }
        return volleyUtils;
    }

    /**
     * get请求
     * @param url 接口
     * @param params 入参参数的集合
     * @param volleyCallBack    数据回调
     */
    public void doGet(String url, Map<String, String> params, final VolleyCallBack volleyCallBack) {
        String httpUrl = url + "?";
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                try {
                    httpUrl += stringStringEntry.getKey() + "=" + URLEncoder.encode(stringStringEntry.getValue(), "UTF-8") + "&";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            httpUrl = url;
        }
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (volleyCallBack != null) {
                    volleyCallBack.success(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (volleyCallBack != null) {
                    volleyCallBack.failure(error);
                }
            }
        });
        requestQueue.add(stringRequest);
    }
    /**
     * post请求
     * @param url 接口
     * @param params 入参参数的集合
     * @param volleyCallBack    数据回调
     */
    public void doPost(String url, final Map<String, String> params, final VolleyCallBack volleyCallBack) {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (volleyCallBack != null) {
                    volleyCallBack.success(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (volleyCallBack != null) {
                    volleyCallBack.failure(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public interface VolleyCallBack {
        void success(String json);

        void failure(Throwable throwable);
    }

}
