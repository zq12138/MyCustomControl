package com.example.admin.mycustomcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.admin.mycustomcontrol.activity.ArcProgressBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_arcProgressBar)
    Button btnArcProgressBar;
    @BindView(R.id.btn_banner)
    Button btnBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    Intent mIntent;

    @OnClick({R.id.btn_arcProgressBar, R.id.btn_banner})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_arcProgressBar:
                mIntent = new Intent(MainActivity.this, ArcProgressBarActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_banner:
                break;
        }
    }
}
