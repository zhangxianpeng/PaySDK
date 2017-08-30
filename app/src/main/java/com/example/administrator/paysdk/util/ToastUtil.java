package com.example.administrator.paysdk.util;
/**
 * Toasttool can make you  use Toast more easy ;
 *
 */
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    /**
     *
     * @param context  The Class's context , where  want to use this tool
     * @param message  The message will be show
     */
    public static void ToastUtil(Context context ,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

}