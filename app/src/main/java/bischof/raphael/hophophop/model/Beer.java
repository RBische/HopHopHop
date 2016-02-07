package bischof.raphael.hophophop.model;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Describes a beer
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class Beer {
    private String id;
    private Label labels;
    private String nameDisplay;
    private ArrayList<Brewery> breweries;
    private BeerStyle style;
    private DateTime createDate;

    /**
     * Returns the image url of the label
     * @return Image url
     */
    @CheckResult
    @Nullable
    public String getLabel() {
        if (labels!=null){
            return labels.getIcon();
        }else{
            return null;
        }
    }

    /**
     * Returns the name of the beer
     * @return Beer name
     */
    @CheckResult
    public String getName() {
        return nameDisplay;
    }

    /**
     * Returns the beer's brewery name or null if there is no brewery
     * @return Brewery name
     */
    @Nullable
    @CheckResult
    public String getBreweryName() {
        if(breweries!=null&&breweries.size()>0){
            return breweries.get(0).getName();
        }else{
            return null;
        }
    }

    /**
     * Returns the style name of the beer.
     * @return Style name
     */
    @Nullable
    @CheckResult
    public String getStyleName() {
        if (style!=null){
            return style.getName();
        }else{
            return null;
        }
    }

    /**
     * Returns a human readable date with text based on phone current {@link Locale}.
     * @param context Context necessary for texts
     * @return Creation date of the beer
     */
    @CheckResult
    public String getFormattedDate(Context context) {
        Locale current = context.getResources().getConfiguration().locale;
        return DateTimeFormat.forPattern("EEE MMM dd yyyy").withLocale(current).print(createDate);
    }
}
