package com.example.admin.mycustomcontrol.retrofit;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by master on 2016/3/3.
 */
public class ActivitySet<T extends Activity> extends HashSet<T> {
    /**
     *
     */
    private static final long serialVersionUID = -9211054184477813134L;

    private ActivitySet() {

    }

    public static ActivitySet<Activity> getInstance() {
        if (activities == null) {
            synchronized (ActivitySet.class) {
                if (activities == null)
                    activities = new ActivitySet<>();
            }

        }
        return activities;

    }

    private static ActivitySet<Activity> activities;

    public void finishAll() {
        for (Activity a : this) {
            if(a==null) continue;
                a.finish();
        }
    }

    public void finishActiviy(Class cla) {
        for (Activity a : this) {
            if(a==null) continue;
            if (a.getClass().equals(cla)) {
                a.finish();
            }
        }
    }

    public void setResult(int result, Class... cla) {
        for (Activity a : this) {
            if(a==null) continue;
            for (int i = 0; i < cla.length; i++) {
                if (a.getClass() == cla[i]) {
                    a.setResult(result);
                    break;
                }
            }
        }
    }

    public void finishOther(Class cla) {
        for (Activity a : this) {
            if(a==null) continue;
            if (a.getClass() != cla) {
                a.finish();
            }
        }
    }

    public void finishOther(Set<Class> classes, int resultCode) {
        for (Activity a : this) {
            if(a==null) continue;
            if (!classes.contains(a.getClass())) {
                a.finish();
                a.setResult(resultCode);
            }
        }
    }

    public void finishOther(int resultCode, Class... cls) {
        for (Activity a : this) {
            if(a==null) continue;
            boolean isContains = false;
            for (int i = 0; i < cls.length; i++) {
                if (cls[i] == a.getClass()) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                a.setResult(resultCode);
                a.finish();
            }
        }
    }


}
