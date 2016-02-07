package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

/**
 * Describes a beer style
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class BeerStyle {
    private int id;
    private String shortName;

    /**
     * Returns the style name of a beer
     * @return Style name
     */
    @CheckResult
    public String getName() {
        return shortName;
    }
}
