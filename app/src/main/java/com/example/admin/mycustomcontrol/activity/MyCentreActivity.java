package com.example.admin.mycustomcontrol.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;



/**
 * Created by zq on 2017/3/6.
 * test
 */

public class MyCentreActivity extends BaseActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        ButterKnife.bind(this);
    }
}
