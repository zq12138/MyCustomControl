package com.example.admin.mycustomcontrol;

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

public class ArcProgressBarActivity extends AppCompatActivity {
    @BindView(R.id.ArcProgressBar)
    ArcProgressBar mArcProgressBar;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_progressbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button2)
    public void onClick() {
        mArcProgressBar.setProgress(0);
    }
}
