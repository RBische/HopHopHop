package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

import io.realm.RealmObject;

/**
 * Describes a label (icon)
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class Label extends RealmObject {
    private String icon;

    @CheckResult
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
