package com.example.admin.mycustomcontrol.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zq on 2017/3/10.
 * BindBankActivity
 *
 * if (msg.what == Constants.ARESDETAILS) {// 获取全国各省及其城市数据
   AreasDetailsBody areaData = (AreasDetailsBody) command.resData;
  if (areaData.getSuccess().equals("true")) {
 */

public class AnimationActivity extends BaseActivity {


    @BindView(R.id.bg_three)
    ImageView bgThree;
    @BindView(R.id.bg_first)
    ImageView bgFirst;
    @BindView(R.id.bg_two)
    ImageView bgTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animation);
        ButterKnife.bind(this);
        initAnimation();
    }

    TranslateAnimation bgFirstAnimation, bgTwoAnimation,bgThreeAnimation;

    private void initAnimation() {
        bgFirstAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        bgTwoAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0f);
        bgThreeAnimation= new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0f);
        bgFirstAnimation.setDuration(6000);
        bgTwoAnimation.setDuration(3000);
        bgThreeAnimation.setDuration(9000);
        bgFirst.startAnimation(bgFirstAnimation);
        bgTwo.startAnimation(bgTwoAnimation);
        bgThree.startAnimation(bgThreeAnimation);
        bgFirstAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgTwo.startAnimation(bgTwoAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bgTwoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgThree.startAnimation(bgThreeAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bgThreeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgFirst.startAnimation(bgFirstAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}