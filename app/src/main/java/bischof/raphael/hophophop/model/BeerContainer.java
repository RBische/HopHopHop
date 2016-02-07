package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

import java.util.ArrayList;

/**
 * Describes a response given by an API call on /beers
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused"})
public class BeerContainer {
    public static int RESULTS_COUNT_PER_PAGE = 50;
    private int currentPage;
    private int numberOfPages;
    private int totalResults;
    private ArrayList<Beer> data;

    public int getTotalResults() {
        return totalResults;
    }

    public int getCurrentPage() {
        return currentPage-1;
    }

    public ArrayList<Beer> getData() {
        return data;
    }

    /**
     * Return a beer at a specified position
     * @param position Position to get
     * @return Beer of specified position
     */
    @CheckResult
    public Beer getBeer(int position) {
        if(getData().size()>position&&position>=0){
            return getData().get(position);
        }else{
            return null;
        }
    }
}
