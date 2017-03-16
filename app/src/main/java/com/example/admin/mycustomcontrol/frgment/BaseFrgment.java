package com.example.admin.mycustomcontrol.frgment;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.admin.mycustomcontrol.activity.BaseActivity;
import com.example.admin.mycustomcontrol.module.IBaseView;

/**
 * Created by zq on 2017/3/16.
 */

public class BaseFrgment extends Fragment implements IBaseView{


    public BaseActivity getBaseActivity(){
        return (BaseActivity) super.getActivity();
    }

    @Override
    public void startActivity(Intent intent) {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void finish() {

    }

    @Override
    public void setResult(int result) {

    }
}
