package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

import io.realm.RealmObject;

/**
 * Describes a brewery
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class Brewery extends RealmObject {
    private String nameShortDisplay;

    @CheckResult
    public String getNameShortDisplay() {
        return nameShortDisplay;
    }

    public void setNameShortDisplay(String nameShortDisplay) {
        this.nameShortDisplay = nameShortDisplay;
    }
}
