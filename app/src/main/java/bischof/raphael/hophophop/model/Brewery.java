package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

/**
 * Describes a brewery
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class Brewery {
    private String nameShortDisplay;

    /**
     * Returns the brewery short name
     * @return Brewery name
     */
    @CheckResult
    public String getName() {
        return nameShortDisplay;
    }
}
