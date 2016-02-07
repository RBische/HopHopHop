package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

/**
 * Describes a label (icon)
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class Label {
    private String icon;

    /**
     * Returns the icon of a label
     * @return Image url
     */
    @CheckResult
    public String getIcon() {
        return icon;
    }
}
