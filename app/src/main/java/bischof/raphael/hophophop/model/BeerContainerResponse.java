package bischof.raphael.hophophop.model;

import android.support.annotation.CheckResult;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Describes a response given by an API call on /beers
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class BeerContainerResponse extends RealmObject {
    public static int RESULTS_COUNT_PER_PAGE = 50;
    private int currentPage;
    private int numberOfPages;
    private int totalResults;
    private int styleId;
    private boolean fromNetwork = true;
    private RealmList<Beer> data;

    @CheckResult
    public int getTotalResults() {
        return totalResults;
    }

    @CheckResult
    public int getStyleId() {
        return styleId;
    }

    @CheckResult
    public boolean isFromNetwork() {
        return fromNetwork;
    }

    @CheckResult
    public int getCurrentPage() {
        return currentPage;
    }

    @CheckResult
    public int getNumberOfPages() {
        return numberOfPages;
    }

    @CheckResult
    public RealmList<Beer> getData() {
        return data;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public void setData(RealmList<Beer> data) {
        this.data = data;
    }

    public void setFromNetwork(boolean fromNetwork) {
        this.fromNetwork = fromNetwork;
    }
}
