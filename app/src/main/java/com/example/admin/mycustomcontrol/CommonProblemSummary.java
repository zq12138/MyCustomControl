package com.example.admin.mycustomcontrol;

/**
 * Created by zq on 2017/2/28.
 */

public class CommonProblemSummary {

/**
 2016  2月29号:
 问题:在是使用侧滑菜单时同时含有轮播图控件此时轮播图滑动事件被侧滑菜单抢占
 问题原因:Android中触摸事件传递过程中dispatchTouchEvent是处理触摸事件分发,事件(多数情况)是从Activity的dispatchTouchEvent开始的。执行
 super.dispatchTouchEvent(ev)，事件向下分发被侧滑菜单拦截。
 处理方式:给轮播图声明@Override
 public boolean dispatchTouchEvent(MotionEvent ev) {
 if (ev.getAction() == MotionEvent.ACTION_DOWN) {
 requestDisallowInterceptTouchEvent(true);
 }
 return super.dispatchTouchEvent(ev);
 }方法此方法可以告诉父容器这个事件我来处理,问题解决。

 2016  3月4号
 get新技能:ListView中的布局滑动时布局展示在title下面效果也是看着很吊。
 实现方式:ListView实现滑动监听在onScroll方法中加入if判断用ListView.getRefreshableView().getFirstVisiblePosition()
 判断滑动到listview布局中的位置  。（getRefreshableView().getFirstVisiblePosition()在滑动监听中会不断更新我们只要做要显示的内容就好了）
 代码的实现: commodity_list.setOnScrollListener(new OnScrollListener() {
@Override public void onScrollStateChanged(AbsListView view, int scrollState) {
}
@Override public void onScroll(AbsListView view, int firstVisibleItem,
int visibleItemCount, int totalItemCount) {
if (commodity_list.getRefreshableView()
.getFirstVisiblePosition() > 1) {
type_Layout.setVisibility(0);
Log.i("test", "-----显示----"
+ commodity_list.getRefreshableView()
.getFirstVisiblePosition());
} else {
type_Layout.setVisibility(8);
Log.i("test", "---------隐藏-----------"
+ commodity_list.getRefreshableView()
.getFirstVisiblePosition());
}

}
});


 2016   4月1号
 问题:首页推荐项目点击过快项目崩溃。
 问题原因:在点击切换项目时使用启动一个子线程来更新UI 速度过快导致开启的线程不能及时关闭内存泄漏。
 实现方式:在没此开启的时候检查未关闭的线程关闭
 get新代码:
 Handler h = new Handler();
 h.removeCallbacksAndMessages(null);
 h.postDelayed(new Runnable() {
 int current = 0;

 @Override public void run() {
 h.removeCallbacksAndMessages(null);
 pbb.setProgress(current);
 current++;
 if (current <= data) {
 h.postDelayed(this, 10);
 }
 }
 }, 10);

 2016.5.30

 Android与H5交互,相应H5页面
 webView.addJavascriptInterface(new InJavaScript(), "injs");
 final class InJavaScript {
 public void runOnAndroidJavaScript(final String str) {
 handler.post(new Runnable() {
 public void run() {
 TextView show = (TextView) findViewById(R.id.textview);
 show.setText(str);
 }
 });
 }
 }

 H5通过  window.injs.runOnAndroidJavaScript(str);//通过injs接口调用android的函数

 Android请求H5
 设置WebViewClient
 webView.setWebViewClient(new WebViewClient(){
 public boolean shouldOverrideUrlLoading(WebView view, String url) {
 view.loadUrl(url);
 return true;
 }
 public void onPageFinished(WebView view, String url) {
 super.onPageFinished(view, url);
 }
 public void onPageStarted(WebView view, String url, Bitmap favicon) {
 super.onPageStarted(view, url, favicon);
 }
 });

 测量listview高度

 @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
 super.onMeasure(widthMeasureSpec, heightMeasureSpec);

 int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

 MeasureSpec.AT_MOST);

 super.onMeasure(widthMeasureSpec, expandSpec);

 }


 2016/11/14
 Android     Studio  配置 butterknife插件
 app      gradle  配置apply plugin: 'android-apt'
 compile 'com.jakewharton:butterknife:8.4.0'
 apt 'com.jakewharton:butterknife-compiler:8.4.0'
 project gradle   classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
 Material Design Library 添加依赖
 compile 'com.android.support:design:24.0.0'

 2016/11/16

 Coordinator
 app:layout_scrollFlags
 scroll
 第一点:如果使用了其他值，必定要使用这个值才能起作用
 第二点:如果在这个child View前面的任何其他ChildView没有设置这个值,
 那么这个Child View的设置将失去作用
 enterAlways
 快速返回模式,其实就是向下滚动时Scrolling View和Child View之间的滚动优先级问题
 对比scroll和scroll|enterAlways设置,发生向下滚动事件时,前者优先滚动Scrolling View,
 后者优先滚动Child View.当优先滚动的一方已经全部滚进屏幕之后.另一方才开始滚动.
 滑动先进先出
 enterAlwaysCollapsed
 enterAlways的附加值这里涉及到Child View的高度和最小高度向下滚动时Child View先向下滚动
 最小高度值然后Scrolling View开始滚动到达边界时Child View再向下滚动直至显示完全
 滑动先进后出
 exitUntilCollapsed
 这里也涉及到最小高度发生向上滚动事件时Child View向上滚动退出直至最小高度然后
 Scrolling View开始滚动也就是Child View不会完全退出屏幕
 滑动不进后出
 snap
 就是Child View滚动比例的一个吸附效果也就是说Child View不会存在局部显示的情况滚动
 ChildView的部分高度当我们松开手指时Child View要么向上全部滚出屏幕要么向下全部滚进屏幕
 有点类似ViewPager的左右滑动
 滑动先进后出吸附效果


 2017/2/6
 Android 数据结构

 队列（Queue）
 单队列
 循环队列
 数组（Array）
 树  （Tree）
 堆  （Heap）
 栈  （Stack）
 图  （Graph）
 链条（Linked List）
 散列表（Hash）

 2017/2/7
 Android 内存泄漏总结

 2017/2/8
 Android Gradle
 最高优先级：buildType 的设置
 次高优先级：productFlavor 的设置
 中等优先级：在 src/main 目录下的 Manifest 文件
 最低优先级：各种依赖和第三方库的设置

 2017/2/20
 问题:轮播图与自定义下拉控件冲突
 问题原因：下拉刷新控件在dispatchTouchEvent做事件处理导致与轮播图事件相互争抢
 导致下拉或者滑动轮播图的时候事件相互争抢出现滑动不流畅
 解决方法:在dispatchTouchEvent  的 ACTION_MOVE 在判断滑动的方向 true执行下拉刷新
 false执行轮播图操作

 2017/2/21
 问题:提取JS收益计算公式计算数值出误差
 问题原因:计算的时候JS中的弱类型double 移植直到Android中整形没有转换成double
 解决方法：整形+d(100d)


 2017/2/26
 问题:

 **/
}
