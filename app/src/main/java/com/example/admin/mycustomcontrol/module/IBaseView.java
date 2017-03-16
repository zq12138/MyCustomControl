package com.example.admin.mycustomcontrol.module;

import android.content.Intent;

import com.example.admin.mycustomcontrol.activity.BaseActivity;

/**
 * Created by zq on 2017/3/16.
 */

public interface IBaseView {
    BaseActivity getBaseActivity();
    void startActivity(Intent intent);
    void startActivityForResult(Intent intent,int requestCode);
    void showError(String message);
    void finish();
    void setResult(int result);
}
