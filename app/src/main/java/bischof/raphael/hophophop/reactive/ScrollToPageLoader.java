package bischof.raphael.hophophop.reactive;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import bischof.raphael.hophophop.HopHopHopApplication;
import bischof.raphael.hophophop.adapter.PagingAdapter;
import bischof.raphael.hophophop.component.AppComponent;
import bischof.raphael.hophophop.model.BeerContainerResponse;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Converts with a flatmap a {@link RecyclerViewScrollEvent} {@link Observable} to a {@link BeerContainerResponse} {@link Observable}
 * Created by biche on 07/02/2016.
 */
public class ScrollToPageLoader implements Func1<RecyclerViewScrollEvent, Observable<BeerContainerResponse>> {
    private final Context mContext;
    private final int mStyleId;
    private final LinearLayoutManager mLayoutManager;
    private int mPageToLoadFirst;
    private boolean mOfflineMode = false;
    private int mNbItemsInOfflineMode = -1;

    public ScrollToPageLoader(Context mContext, int styleId, int pageToLoadFirst, LinearLayoutManager layoutManager) {
        this.mContext = mContext;
        this.mStyleId = styleId;
        this.mPageToLoadFirst = pageToLoadFirst;
        this.mLayoutManager = layoutManager;
    }

    /**
     * If set true, force the scroll pager to load all his items from DB and to count at the first call the number of items that it has
     * @param offlineMode Mode in which you want to switch
     */
    public void setOfflineMode(boolean offlineMode) {
        this.mOfflineMode = offlineMode;
        if(!offlineMode){
            mNbItemsInOfflineMode = -1;
        }
    }

    @Override
    public Observable<BeerContainerResponse> call(RecyclerViewScrollEvent scrollEvent) {
        int pageToLoad = findPageToLoad(scrollEvent);
        Realm realm = Realm.getInstance(mContext);
        if (!mOfflineMode){
            return getResultFromOnlineMode(pageToLoad, realm);
        }else{
            return getResultFromOfflineMode(pageToLoad, realm);
        }
    }

    /**
     * Returns the page that must be loaded
     * @param scrollEvent The event that helps to calculate the page to load
     * @return The page to load
     */
    private int findPageToLoad(RecyclerViewScrollEvent scrollEvent) {
        int pageToLoad = mPageToLoadFirst;
        if(scrollEvent.view().getAdapter() instanceof PagingAdapter){
            PagingAdapter adapter = (PagingAdapter)scrollEvent.view().getAdapter();
            int currentPage = adapter.getCurrentPage();
            if (scrollEvent.dy()>0){
                int nextPage = ((mLayoutManager.findLastVisibleItemPosition()+ScrollFilter.LOADING_THRESHOLD)/BeerContainerResponse.RESULTS_COUNT_PER_PAGE)+1;
                //If we scroll too fast down, then we have to load the page currently shown
                if (nextPage>currentPage+1){
                    pageToLoad = nextPage-1;
                }else{
                    pageToLoad = currentPage+1;
                }
            }else if (scrollEvent.dy()<0){
                int previousPage = (mLayoutManager.findFirstVisibleItemPosition()-ScrollFilter.LOADING_THRESHOLD)/BeerContainerResponse.RESULTS_COUNT_PER_PAGE;
                //If we scroll too fast up, then we have to load the page currently shown
                if (previousPage<currentPage-1){
                    pageToLoad = previousPage+1;
                }else{
                    pageToLoad = currentPage-1;
                }
            }
        }
        mPageToLoadFirst = 1;
        return pageToLoad;
    }

    /**
     * Method that switch the page load call between already stored data or a new retrofit call
     * @param pageToLoad The page that needs to be loaded
     * @param realm An instance of realm db
     * @return Returns an {@link Observable<BeerContainerResponse>} that contains either a retrofit call or already loaded data from DB
     */
    private Observable<BeerContainerResponse> getResultFromOnlineMode(int pageToLoad, Realm realm) {
        RealmResults<BeerContainerResponse> results = realm.where(BeerContainerResponse.class)
                .equalTo("styleId", mStyleId)
                .equalTo("currentPage", pageToLoad)
                .findAll();
        if (results.size()>0){
            List<BeerContainerResponse> response = realm.copyFromRealm(results);
            return Observable.from(response);
        }else{
            AppComponent component = HopHopHopApplication.getInstance().getAppComponent();
            return component.manager().getBeers(mStyleId, pageToLoad);
        }
    }

    /**
     *
     * @param pageToLoad The page that needs to be loaded
     * @param realm An instance of realm db
     * @return Returns an {@link Observable<BeerContainerResponse>} that contains data from DB
     */
    private Observable<BeerContainerResponse> getResultFromOfflineMode(int pageToLoad, Realm realm) {
        RealmResults<BeerContainerResponse> results = realm.where(BeerContainerResponse.class)
                .equalTo("styleId", mStyleId)
                .findAllSorted("currentPage");
        if (results.size()>0){
            if (mNbItemsInOfflineMode==-1){
                BeerContainerResponse lastContainer = results.get(results.size() - 1);
                mNbItemsInOfflineMode = (results.size()-1)*BeerContainerResponse.RESULTS_COUNT_PER_PAGE+lastContainer.getData().size();
                pageToLoad = 1;
            }
            BeerContainerResponse currentContainer = realm.copyFromRealm(results.get(pageToLoad-1));
            currentContainer.setCurrentPage(pageToLoad);
            currentContainer.setTotalResults(mNbItemsInOfflineMode);
            return Observable.from(new BeerContainerResponse[]{currentContainer});
        }else{
            return Observable.from(new BeerContainerResponse[]{});
        }
    }
}
