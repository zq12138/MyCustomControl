package com.example.admin.mycustomcontrol.retrofit;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zq on 2017/3/16.
 */

public class ServiceCreater {

    private static ServiceApi serviceApi = createrApi();

    public static ServiceApi getServiceApiInstance() {
        return serviceApi;
    }


    public ServiceCreater() {

    }
//    https://api2.etongdai.com/service/system/userFailure?errorType=-1
    public static ServiceApi createrApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api2.etongdai.com/").
                addConverterFactory(GsonConverterFactory.create()).
                client(createOkHttpClient()).
                build();
        return retrofit.create(ServiceApi.class);
    }

    public static OkHttpClient createOkHttpClient() {


        return new OkHttpClient.Builder().
                addInterceptor(new LogInterceptor())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return false;
                    }
                })
                .sslSocketFactory(createSSLSocket())
                .build();
    }

    private static SSLSocketFactory createSSLSocket() {
        try {
            SSLContext ssl = SSLContext.getInstance("SSL");
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            };
            ssl.init(null, new TrustManager[]{xtm}, new SecureRandom());
            return ssl.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }
}
