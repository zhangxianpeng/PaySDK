package com.example.administrator.paysdk.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.paysdk.R;
import com.example.administrator.paysdk.javabean.Gatstation;
import com.example.administrator.paysdk.webservice.GetRequest_Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/28/028.
 */

public class DoRequestActivity extends AppCompatActivity {
    private Button btn_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetRequest();
            }
        });

    }

    private void doGetRequest() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Gatstation> call = request.getCall("b77d71a00bf186387daf0cb4a4c1906d",2,"深圳");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Gatstation>() {
            //请求成功时候的回调
            @Override
            public void onResponse(Call<Gatstation> call, Response<Gatstation> response) {
                //请求处理,输出结果
                response.body().toString();
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<Gatstation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }
}
