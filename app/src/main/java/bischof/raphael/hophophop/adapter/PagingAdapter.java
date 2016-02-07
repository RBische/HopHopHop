package bischof.raphael.hophophop.adapter;

/**
 * Interface used in case of an adapter with pages
 * Created by biche on 07/02/2016.
 */
public interface PagingAdapter {

    /**
     * Returns the index of the page shown currently in the adapter
     * @return Index of the page
     */
    int getCurrentPage();

    /**
     * Returns true if the adapter is currently loading a page
     * @return Boolean representing loading state
     */
    boolean isLoading();

    /**
     * Set the loading state of the adapter
     * @param loading Boolean representing the state
     */
    void setLoading(boolean loading);
}
