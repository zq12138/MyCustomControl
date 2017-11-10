package com.example.admin.mycustomcontrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * test
 * test
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_arcProgress)
    Button btnArcProgress;
    @BindView(R.id.btn_recycler)
    Button btnRecycler;
    @BindView(R.id.btn_wed)
    Button btnWed;
    @BindView(R.id.btn_okhttp)
    Button btnOkhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_arcProgress, R.id.btn_recycler, R.id.btn_wed, R.id.btn_okhttp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_arcProgress:
                startActivity(ArcProgressBarActivity.class);
                break;
            case R.id.btn_recycler:
                startActivity(AnimationActivity.class);
                break;
            case R.id.btn_wed:
                startActivity(WedViewActivity.class);
                break;
            case R.id.btn_okhttp:
                startActivity(OkHttpActivity.class);
                break;
        }
    }
}
