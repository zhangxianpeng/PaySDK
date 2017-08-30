package com.example.administrator.paysdk.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.paysdk.R;
import com.example.administrator.paysdk.util.ToastUtil;

import java.util.Hashtable;

public class AddoilActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String[] gastType={"b90","b93","b97","b0"};
    private static final String[] perprice = {"6.07","6.51","7.04","6.11"};
    private TextView tv_money_result;
    private EditText et_much;
    private Button btn_commit,btn_get,btn_history;
    private ImageButton imgbtn_calculator;
    private ImageView iv_code;
    private Spinner spinner_gastType,spinner_perprice;
    private ArrayAdapter<String> adapter_gastType,adapter_perprice;
    private final int QR_WIDTH = 300;
    private final int QR_HEIGHT = 300;
    Float result;
    String money,much,perprice_data,gast_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_addoil);

        //默认初始化
        //Bmob.initialize(this,"c9d788e6da036be0240ab1804c84e2da");

        tv_money_result = (TextView) findViewById(R.id.tv_money_result);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        btn_get = (Button)findViewById(R.id.btn_get);
        btn_history = (Button)findViewById(R.id.btn_history_order);
        spinner_gastType = (Spinner)findViewById(R.id.spinner_gastType);
        spinner_perprice = (Spinner)findViewById(R.id.spinner_perprice);
        et_much = (EditText)findViewById(R.id.et_much);
        imgbtn_calculator = (ImageButton)findViewById(R.id.imgbtn_calculator);
        iv_code =(ImageView)findViewById(R.id.iv_code);


        btn_commit.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        btn_history.setOnClickListener(this);
        imgbtn_calculator.setOnClickListener(this);

        adapter_gastType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gastType);
        adapter_perprice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, perprice);

        //设置下拉列表的风格
        adapter_gastType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_perprice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //将adapter 添加到spinner中
        spinner_gastType.setAdapter(adapter_gastType);
        spinner_perprice.setAdapter(adapter_perprice);

        //添加事件Spinner事件监听
        spinner_gastType.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner_perprice.setOnItemSelectedListener(new SpinnerSelectedListener());

        //设置默认值
        spinner_gastType.setVisibility(View.VISIBLE);
        spinner_perprice.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_commit:
               // StoreData();
                Gotopay();
                break;
            case R.id.imgbtn_calculator:
                Calculate();
                break;
            case R.id.btn_get:
               // GetQRCode();
//                Intent get = new Intent(AddOil.this,QRCodeActivity.class);
//                startActivity(get);
                break;
            case R.id.btn_history_order:
                /*Intent intent = new Intent(AddOil.this,HistoryOrderActivity.class);
                startActivity(intent);*/
                break;
            default:
                break;
        }

    }

    private void Gotopay() {
        ToastUtil.ToastUtil(this,"唤起支付设备");
    }


    private void Calculate() {
        much = et_much.getText().toString();
        perprice_data= (String)spinner_perprice.getSelectedItem();

        Log.v("1",much);

        Log.v("2",perprice_data);

        if(much!=null){
            float m = Float.valueOf(much);
            float p = Float.valueOf(perprice_data);
            result = m*p;
            tv_money_result.setText(String.valueOf(result));
        }else {
            ToastUtil.ToastUtil(this,"输入不能为空");
        }

    }

    /*private void StoreData() {
        money = String.valueOf(result);
        much = et_much.getText().toString();
        perprice_data = (String)spinner_perprice.getSelectedItem();
        gast_type = (String)spinner_gastType.getSelectedItem();

        Order order = new Order();
        order.setOil_much(much);
        order.setOil_money(money);
        order.setOil_perprice(perprice_data);
        order.setOil_type(gast_type);

        order.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    ToastUtil.ToastUtil(AddOil.this,"订单提交成功");
                }else {
                    ToastUtil.ToastUtil(AddOil.this,"服务器繁忙，请重试");
                }
            }
        });
    }


    private void GetQRCode() {
        Hashtable<EncodeHintType,String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");

        //图像数据转换，使用矩阵转换
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode("汽油类型："+gast_type+"\n"+"汽油单价："+perprice_data+"\n"+"预加油量："+much+"\n"+"需付金额："+money+"\n",
                    BarcodeFormat.QR_CODE,QR_WIDTH,QR_HEIGHT,hints);

            int[] pixels = new int[QR_WIDTH*QR_HEIGHT];

            for(int y= 0;y<QR_HEIGHT;y++){
                for(int x = 0;x<QR_WIDTH;x++){
                    if(bitMatrix.get(x,y)){
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    }else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
                //生成二维码图片的格式，使用ARGB_8888
                Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
                //显示到一个ImageView上面
                iv_code.setImageBitmap(bitmap);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
*/

    private class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}


