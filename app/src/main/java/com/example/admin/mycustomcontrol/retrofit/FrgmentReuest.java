package com.example.admin.mycustomcontrol.retrofit;

import com.example.admin.mycustomcontrol.activity.BaseActivity;
import com.example.admin.mycustomcontrol.frgment.BaseFrgment;
import com.example.admin.mycustomcontrol.retrofit.bean.BaseBean;

/**
 * Created by zq on 2017/3/16.
 */

public abstract class FrgmentReuest<E extends BaseBean, A extends BaseFrgment> extends DialogRequest<E, A> {
    public FrgmentReuest(A attachTarget) {
        super(attachTarget);
    }

    @Override
    protected BaseActivity getBaseActivity() {
        return getAttachTarget().getBaseActivity();
    }
}
