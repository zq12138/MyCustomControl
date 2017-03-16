package com.example.admin.mycustomcontrol.retrofit;

import com.example.admin.mycustomcontrol.activity.BaseActivity;
import com.example.admin.mycustomcontrol.module.IBaseView;
import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;

/**
 * Created by zq on 2017/3/16.
 */

public abstract class ViewRequest<E extends BaseBean, T extends IBaseView> extends DialogRequest<E, T> {
    public ViewRequest(T attachTarget) {
        super(attachTarget);
    }

    @Override
    protected BaseActivity getBaseActivity() {
        if (getAttachTarget() == null) return null;
        return getAttachTarget().getBaseActivity();
    }
}
