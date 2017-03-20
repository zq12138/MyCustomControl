package com.example.admin.mycustomcontrol.retrofit;

import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zq on 2017/3/16.
 */

public abstract class Request<E extends BaseBean> {
    ServiceApi api;

    public Request() {
        api = getApi();
    }

    private ServiceApi getApi() {
        return ServiceCreater.getServiceApiInstance();
    }


    Call<E> call;

    protected void send() {
        call = newCall(api);
        onPreRequest();
        call.enqueue(new Callback<E>() {
            @Override
            public void onResponse(Call<E> call, Response<E> response) {
                E e = response.body();
                Request.this.onResponse(e);
            }

            @Override
            public void onFailure(Call<E> call, Throwable t) {
                Request.this.onFailure();
            }
        });
    }

    /**
     * 请求成功
     */

    protected void onResponse(E e) {

        if (e.isSuccess()) {
            onResponseSuccess(e);
        } else {
            onResponseDailure(e);

        }

    }

    // 会话超时
    public static final String SESSION_TIMEOUT_CODE = "800000";

    // 用户被挤掉
    protected static final String LOGIN_CROWD_OUT_CODE = "800001";

    /**
     * 返回失败信息的时候调用
     */
    protected void onResponseDailure(E e) {
        if (SESSION_TIMEOUT_CODE.equals(e.getCode())) {
            onResponseFailureSessionExpired(e);
        } else if (LOGIN_CROWD_OUT_CODE.equals(e.getCode())) {
            onResponseFailureOtherSignIn(e);
        } else {
            onResponseFailureMessage(e);
        }

    }

    /**
     * 异地登录
     *
     * @param e
     */
    protected abstract void onResponseFailureOtherSignIn(E e);

    /**
     * 回话失效
     *
     * @param e
     */
    protected abstract void onResponseFailureSessionExpired(E e);

    /**
     * 错误信息
     *
     * @param e
     */
    protected abstract void onResponseFailureMessage(E e);


    /**
     * 请求成功
     */
    protected abstract void onResponseSuccess(E e);

    /**
     * 请求失败
     */
    protected abstract void onFailure();

    protected abstract Call<E> newCall(ServiceApi api);


    protected abstract void onPreRequest();
}
