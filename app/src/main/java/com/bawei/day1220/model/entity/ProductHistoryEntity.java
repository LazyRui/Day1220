package com.bawei.day1220.model.entity;

import java.util.List;

/**
 * ProjectName: Day1220
 * PackageName: com.bawei.day1220.model
 * ClassName:   ProductHistoryEntity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/20_10:50
 */
public class ProductHistoryEntity {

    /**
     * msg : 响应成功
     * code : 200
     * tags : ["手机壁纸","手机app","手机cpu天梯图","手机号码测吉凶","手机新浪网","手机号码测吉凶(超准)","手机电影","手机在线","手机排名","小米手机"]
     */

    private String msg;
    private int code;
    private List<String> tags;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
