package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

import io.realm.RealmObject;

/**
 * Describes a beer style
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class BeerStyle extends RealmObject {
    private int id;
    private String shortName;

    @CheckResult
    public String getShortName() {
        return shortName;
    }

    @CheckResult
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
