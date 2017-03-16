package com.example.admin.mycustomcontrol.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by zq on 2017/3/16.
 */

public class LogInterceptor implements Interceptor {
    private static String TAG = "LogInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        //打印request信息
        logRequest(request);

        long startTime = System.currentTimeMillis();

        //请求
        okhttp3.Response response = chain.proceed(chain.request());

        //计算时间
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;

        //提取数据
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        logResponse(duration, content);

        //response内容只能获取一次，所以要重新构建一个response返回
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }

    private void logResponse(long duration, String content) {
        Log.w(TAG, "| Response:" + content);
        Log.w(TAG,"----------End:"+duration+"毫秒----------");
    }

    private void logRequest(okhttp3.Request request) {
        Log.w(TAG,"\n");
        Log.w(TAG,"----------Start----------------");
        Log.w(TAG, "| "+request.toString());
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.w(TAG, "| RequestParams:{"+sb.toString()+"}");
            }
        }
    }
}
