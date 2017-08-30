package com.example.administrator.paysdk.util;

/**回调服务返回的结果
 * Created by Administrator on 2017/1/10.
 */
public interface HttpCallbackListener {
     void onFinish(String response) ;
    void onError(Exception e);
}
 