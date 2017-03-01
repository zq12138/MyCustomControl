package com.example.admin.mycustomcontrol.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.admin.mycustomcontrol.widget.ArcProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zq on 2017/3/1.
 */

public class ArcProgressBarActivity extends AppCompatActivity{
    @BindView(com.example.admin.mycustomcontrol.R.id.ArcProgressBar)
    ArcProgressBar mArcProgressBar;
    @BindView(com.example.admin.mycustomcontrol.R.id.button2)
    Button button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.admin.mycustomcontrol.R.layout.activity_arc_progressbar);
        ButterKnife.bind(this);
    }

    @OnClick(com.example.admin.mycustomcontrol.R.id.button2)
    public void onClick() {
        mArcProgressBar.setProgress(0);
    }
}
