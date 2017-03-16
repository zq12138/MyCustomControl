package com.example.admin.mycustomcontrol.retrofit;

import com.example.admin.mycustomcontrol.activity.BaseActivity;
import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;

/**
 * Created by zq on 2017/3/16.
 */

public abstract class ActivityRequest<E extends BaseBean, A extends BaseActivity> extends DialogRequest<E, A> {
    public ActivityRequest(A attachTarget) {
        super(attachTarget);
    }

    @Override
    protected BaseActivity getBaseActivity() {
        return getAttachTarget();
    }

}
