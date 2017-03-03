package com.example.admin.mycustomcontrol.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.mycustomcontrol.R;
import com.example.admin.mycustomcontrol.utils.ImageWapper;
import com.example.admin.mycustomcontrol.widget.ArcProgressBar;
import com.example.admin.mycustomcontrol.widget.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zq on 2017/3/1.
 */

public class ArcProgressBarActivity extends AppCompatActivity {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ArcProgressBar)
    ArcProgressBar mArcProgressBar;
    @BindView(R.id.button2)
    Button button2;
    List<String> lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_progressbar);
        ButterKnife.bind(this);
        lists = new ArrayList<>();
        lists.add("http://upload-images.jianshu.io/upload_images/1252638-3f820ebf10110ddc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        banner.setUris(lists, new Banner.DiaplayImageCallback() {
            @Override
            public void displayImage(String url, ImageView view) {
                ImageWapper.with(ArcProgressBarActivity.this).load(url).into(view);
            }
        });
    }


    @OnClick(R.id.button2)
    public void onClick() {
        mArcProgressBar.setProgress(0);
    }
}
