package com.example.admin.mycustomcontrol.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.admin.mycustomcontrol.retrofit.ActivityRequest;
import com.example.admin.mycustomcontrol.retrofit.ServiceApi;
import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;


/**
 * Created by zq on 2017/3/8.
 */

public class OkHttpActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        new requestInfo(this).send();
    }


    static class requestInfo extends ActivityRequest<BaseBean, OkHttpActivity> {

        public requestInfo(OkHttpActivity attachTarget) {
            super(attachTarget);
        }


        @Override
        protected void onResponseSuccess(BaseBean baseBean) {

        }

        @Override
        protected retrofit2.Call<BaseBean> newCall(ServiceApi api) {
            return api.requestAutoBidSwitch("", "", "", "");
        }
    }


    /**
     * GET请求
     */
    public void okGet() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        Response response = okHttpClient.newCall(request).execute();
    }

    /**
     * POST请求
     */
    public void okPost() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, "");
        Request request = new Request.Builder().post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
    }

    /**
     * POST键值对
     */
    public void okPostKey() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody requestBody = new FormBody.Builder().add("", "").build();
        Request request = new Request.Builder().url("").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
    }

    /**
     * 同步Get
     */

    public void run() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            Log.i("tag", headers.name(i) + "--" + headers.value(i));
        }
        Log.i("tag", response.body().string());
    }

    /**
     * Get异步
     */
    public void asyncRun() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.i("tag", headers.name(i) + "--" + headers.value(i));
                }
                Log.i("tag", response.body().string());
            }
        });
    }

    /**
     * 提取响应头
     */

    public void responseHead() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json").build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {

        }
    }

    /**
     * Post方式提交String
     */
    public void mPostString() throws IOException {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        Request request = new Request.Builder().url("").post(RequestBody.create(mediaType, postBody)).build();
        Response respons = okHttpClient.newCall(request).execute();
    }

    /**
     * Post方式提交流
     */
    MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");

    public void mPostStream() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };
        Request request = new Request.Builder().url("").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        Log.i("tag", "mPostStream: " + response.body().string());
    }

    /**
     * POST方式文件
     */

    public void mPostFile() throws IOException {
        OkHttpClient okhttpclient = new OkHttpClient();
        File file = new File("");
        Request request = new Request.Builder().post(RequestBody.create(mediaType, file)).build();
        Response response = okhttpclient.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        Log.i("tag", "mPostStream: " + response.body().string());
    }

    /**
     * GSON解析JSON
     */
    Gson gson = new Gson();

    public void mPostGson() throws IOException {
        OkHttpClient okhttpclient = null;
        Request request = null;
        Response response = okhttpclient.newCall(request).execute();
        Gist gist = gson.fromJson(response.body().charStream(), Gist.class);

        for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }
    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }
}
