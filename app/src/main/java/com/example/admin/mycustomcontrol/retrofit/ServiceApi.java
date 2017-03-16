package com.example.admin.mycustomcontrol.retrofit;

import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zq on 2017/3/16.
 */

public interface ServiceApi {

    /**
     * 关闭自动投标
     */
    @POST("service/autobid/openClose")
    @FormUrlEncoded
    Call<BaseBean> requestAutoBidSwitch(@Field("useId") String useId,
                                        @Field("sessionId") String sessionId,
                                        @Field("openClose") String state,
                                        @Field("bidOperateTermimal") String type);
}
