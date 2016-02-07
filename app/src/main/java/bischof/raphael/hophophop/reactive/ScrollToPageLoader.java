package bischof.raphael.hophophop.reactive;

import android.content.Context;

import bischof.raphael.hophophop.adapter.PagingAdapter;
import bischof.raphael.hophophop.model.BeerContainer;
import bischof.raphael.hophophop.modules.ApiComponent;
import bischof.raphael.hophophop.modules.ApiModule;
import bischof.raphael.hophophop.modules.DaggerApiComponent;
import rx.Observable;
import rx.functions.Func1;

/**
 * Converts with a flatmap a {@link RecyclerViewScrollEvent} {@link Observable} to a {@link BeerContainer} {@link Observable}
 * Created by biche on 07/02/2016.
 */
public class ScrollToPageLoader implements Func1<RecyclerViewScrollEvent, Observable<BeerContainer>> {
    private final Context mContext;

    public ScrollToPageLoader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Observable<BeerContainer> call(RecyclerViewScrollEvent scrollEvent) {
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
        ApiComponent component = DaggerApiComponent.builder().apiModule(new ApiModule(mContext)).build();
        return component.manager().getBeers("595f9b678d9a88434f7e5c72f8cfac38", 30, pageToLoad);
    }
}
