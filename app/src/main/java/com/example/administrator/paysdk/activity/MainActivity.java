package com.example.administrator.paysdk.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.paysdk.R;
import com.example.administrator.paysdk.adapter.MyAdapter;
import com.example.administrator.paysdk.javabean.GastStation;
import com.example.administrator.paysdk.util.HttpCallbackListener;
import com.example.administrator.paysdk.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private Button btn_search;
    private MyAdapter myAdapter;
    private List<Gatstation> gatstationList;

    public static final int SHOW_RESPONSE = 1;   //标志位

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    if (response != null) {
                        //接受到应答，解析Json数据
                        List<Gatstation> gastStations = parseWithJSON(response);
                        gatstationList.addAll(gastStations);
                        myAdapter.notifyDataSetChanged();
                        Log.v("1",response);
                    }
            }

        }
    };

    private List<Gatstation> parseWithJSON(String response) {
        List<Gatstation> gastStations = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("result");
            JSONArray data = jsonObject.getJSONArray("data");
            Gatstation gatStation;
            for(int i = 0; i<data.length(); i++){
                gatStation = new Gatstation();
                JSONObject gastJson = data.getJSONObject(i);
                gatStation.setName(gastJson.getString("name"));
                gatStation.setAreaname(gastJson.getString("areaname"));
                gatStation.setAddress(gastJson.getString("address"));
                gastStations.add(gatStation);
                Log.v("2",gatStation.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gastStations;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        recyclerView.setAdapter(myAdapter);
        myAdapter = new MyAdapter(this,gatstationList);
        initView();

    }

    private void initView() {
        btn_search =(Button)findViewById(R.id.btn_search);
        recyclerView = (RecyclerView)findViewById(R.id.rL_item);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击实现网络请求",Toast.LENGTH_SHORT).show();
                //doRequestWithRetrofit();
                doRequestWithHttpConnection();

            }
        });


    }

    *//**
     * 用HttpConnection框架去处理网络请求的内容
     * 使用定制LogUtil打印输出测试是否请求成功并有数据返回
     *//*
    private void doRequestWithHttpConnection() {
        String url = "http://apis.juhe.cn/oil/region?key=b77d71a00bf186387daf0cb4a4c1906d&format=2&city=%E5%8C%97%E4%BA%AC%E5%B8%82";
        HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                //将服务器返回的结果存放到Message中
                message.obj = response.toString();
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                System.out.println("获取相关信息失败");
            }
        });

    }
    *//**
     * 用Retrofit框架去处理网络请求的内容
     * 使用定制LogUtil打印输出测试是否请求成功并有数据返回
     *//*
    public void doRequestWithRetrofit() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Gatstation> call = request.getCall();

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
}*/


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, MyAdapter.OnItemClickListener{
    private Button btn_search;
    private RecyclerView recyclerView;

    private MyAdapter recyclerAdapter;
    private List<GastStation> gastStationList;



    public static final int SHOW_RESPONSE = 1;   //标志位


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    if (response != null) {
                        //接受到应答，解析Json数据
                        List<GastStation> gastStations = parseWithJSON(response);

                        gastStationList.addAll(gastStations);

                        recyclerAdapter.notifyDataSetChanged();

                        Log.v("1",response);
                    }
            }

        }
    };



    private List<GastStation> parseWithJSON(String response) {
        List<GastStation> gastStations = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("result");
            JSONArray data = jsonObject.getJSONArray("data");

            GastStation gastStation;
            for(int i = 0; i<data.length(); i++){
                gastStation = new GastStation();
                JSONObject gastJson = data.getJSONObject(i);
                gastStation.setName(gastJson.getString("name"));
                gastStation.setAreaName(gastJson.getString("areaname"));
                gastStation.setAddress(gastJson.getString("address"));

                gastStations.add(gastStation);

                Log.v("2",gastStation.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gastStations;


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gastStationList = new ArrayList<>();
        recyclerAdapter = new MyAdapter(this, gastStationList);

        initView();
    }

    private void initView() {
        btn_search = (Button)findViewById(R.id.btn_search);
        recyclerView = (RecyclerView)findViewById(R.id.rL_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);


        btn_search.setOnClickListener(this);

        recyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                getCityRequest();
                break;
            default:
                break;
        }
    }


    //使用城市获取加油站列表
    private void getCityRequest() {
        String url = "http://apis.juhe.cn/oil/region?key=b77d71a00bf186387daf0cb4a4c1906d&format=2&city=%E6%B7%B1%E5%9C%B3";
        HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                //将服务器返回的结果存放到Message中
                message.obj = response.toString();
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                System.out.println("获取相关信息失败");
            }
        });

    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent (MainActivity.this,AddoilActivity.class);
        startActivity(intent);
    }
}
