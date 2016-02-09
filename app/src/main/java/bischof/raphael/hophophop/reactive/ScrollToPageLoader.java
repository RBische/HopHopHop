package bischof.raphael.hophophop.reactive;

import android.content.Context;

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

    public ScrollToPageLoader(Context mContext, int styleId) {
        this.mContext = mContext;
        this.mStyleId = styleId;
    }

    @Override
    public Observable<BeerContainerResponse> call(RecyclerViewScrollEvent scrollEvent) {
        int pageToLoad = 1;
        if(scrollEvent.view().getAdapter() instanceof PagingAdapter){
            PagingAdapter adapter = (PagingAdapter)scrollEvent.view().getAdapter();
            int currentPage = adapter.getCurrentPage();
            if (scrollEvent.dy()>0){
                pageToLoad = currentPage+1;
            }else if (scrollEvent.dy()<0){
                pageToLoad = currentPage-1;
            }
        }
        Realm realm = Realm.getInstance(mContext);
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
}
