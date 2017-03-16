package com.example.admin.mycustomcontrol.retrofit;

import android.app.Dialog;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.mycustomcontrol.activity.BaseActivity;
import com.example.admin.mycustomcontrol.activity.R;
import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;

import java.lang.ref.WeakReference;

/**
 * Created by zq on 2017/3/16.
 */

public abstract class DialogRequest<E extends BaseBean, T> extends Request<E> {
    public static final int FLAG_DEFAULT = 0;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int flag = FLAG_DEFAULT;
    WeakReference<T> weakReference;

    WeakReference<Dialog> dialogWeakReference;

    public DialogRequest(T attachTarget) {
        super();
        weakReference = new WeakReference<>(attachTarget);
    }

    public void send() {
        if (isViewGone()) return;
        super.send();

    }


    boolean isShowDialog = true;

    @Override
    protected void onPreRequest() {
        if (isShowDialog) {
            if (dialogWeakReference == null || dialogWeakReference.get() == null) {
                Dialog dialog = createDialog();
                if (dialog == null) return;
                dialogWeakReference = new WeakReference<>(dialog);
            }
            dialogWeakReference.get().show();
        }
    }

    private Dialog createDialog() {
        Dialog dialog = getBaseActivity().createDialog(R.style.customDialogTheme);
        dialog.setContentView(R.layout.wait_progress_dialog);
        return dialog;
    }

    private boolean isViewGone() {

        if (weakReference.get() == null) {
            dialogDismiss();
            return true;
        }

        if (!getBaseActivity().isAlive()) {
            return true;
        }

        return false;
    }


    protected abstract BaseActivity getBaseActivity();

    /**
     * 网络请求失败 如果界面已经被回收，则直接返回
     */
    @Override
    protected void onFailure() {
        if (isViewGone()) return;
        dialogDismiss();
        Log.i("tag", "网络不给力，请检查您的网络状态后重试");
    }

    @Override
    protected void onResponse(E e) {
        if (isViewGone()) return;
        dialogDismiss();
        super.onResponse(e);
    }

    /**
     * 错误信息
     *
     * @param e
     */
    @Override
    protected void onResponseFailureMessage(E e) {
        Toast.makeText(getBaseActivity(), e.getInfo(), Toast.LENGTH_LONG).show();
    }

    /**
     * 异地登录
     *
     * @param e
     */
    @Override
    protected void onResponseFailureOtherSignIn(E e) {
        sessionInvalid(e);
    }

    private void sessionInvalid(E e) {
//        YiTongDaiApplication application = YiTongDaiApplication.sApplication;
//        YiTongDaiApplication.clearUserInfo();
//        GesturePasswordUtils.clear(application);
//        ErrorDialogUtil.showAlertDialog(getBaseActivity(),
//                e.getInfo(), "返回首页",
//                new ETongDaiDialogs.ETongDaiDialogListeners() {
//                    @Override
//                    public void OnHintConfirmClicked(
//                            ETongDaiDialogs dialog) {
//                        HomeActivity.startActivity(getBaseActivity(), R.id.home_page, null);
//                        dialog.dismiss();
//                    }
//                });
    }

    /**
     * session失效
     *
     * @param e
     */
    @Override
    protected void onResponseFailureSessionExpired(E e) {
        sessionInvalid(e);
    }

    private void dialogDismiss() {
        if (dialogWeakReference != null && dialogWeakReference.get() != null && dialogWeakReference.get().isShowing()) {
            dialogWeakReference.get().dismiss();
        }
    }


    protected T getAttachTarget() {
        return weakReference.get();
    }

}
