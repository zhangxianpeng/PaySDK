package com.example.administrator.paysdk.webservice;

import com.example.administrator.paysdk.javabean.Gatstation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/8/28/028.
 * 返回接口
 */

public interface GetRequest_Interface {

    /**
     * 第一部分
     * 在网络请求接口的注释设置
     */
    @GET("oil/region")
    //@get注解的作用：采用get方法发送网络请求
    Call<Gatstation> getCall(@Query("key") String key, @Query("format") int format, @Query("city") String city
    );
    //getCall() 接收网络请求数据的方法，如果想直接获得Responsebody中的内容,可以定义网络请求返回值为Call<Responsebody>

}