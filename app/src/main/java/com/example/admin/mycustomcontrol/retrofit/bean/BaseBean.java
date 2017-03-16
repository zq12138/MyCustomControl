package com.example.admin.mycustomcontrol.retrofit.bean;

/**
 * Created by zq on 2017/3/16.
 */

public class BaseBean {


    private long time;
    private boolean success;
    private String code;
    private String info;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
