package bischof.raphael.hophophop.reactive;

import android.support.v7.widget.LinearLayoutManager;

import bischof.raphael.hophophop.adapter.PagingAdapter;
import bischof.raphael.hophophop.model.BeerContainerResponse;
import rx.functions.Func1;

/**
 * Allows to filter {@link RecyclerViewScrollEvent} {@link rx.Observable} that would fire a page changing
 * Created by biche on 07/02/2016.
 */
public class ScrollFilter implements Func1<RecyclerViewScrollEvent, Boolean> {
    private static final int LOADING_THRESHOLD = 15;
    private final LinearLayoutManager mLayoutManager;

    public ScrollFilter(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public Boolean call(RecyclerViewScrollEvent scrollEvent) {
        boolean mustBeLoaded = false;
        boolean loading = false;
        if (scrollEvent.dx()==0&&scrollEvent.dy()==0) {
            int itemCount = scrollEvent.view().getAdapter().getItemCount();
            if (itemCount==0){
                mustBeLoaded = true;
            }
        }
        if(scrollEvent.view().getAdapter() instanceof PagingAdapter){
            PagingAdapter adapter = (PagingAdapter)scrollEvent.view().getAdapter();
            int currentPage = adapter.getCurrentPage();
            int itemCount = scrollEvent.view().getAdapter().getItemCount();
            if (scrollEvent.dy()>0){
                int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                if (Math.min(lastVisiblePosition + LOADING_THRESHOLD, itemCount) > BeerContainerResponse.RESULTS_COUNT_PER_PAGE*(currentPage)){
                    mustBeLoaded = true;
                }
            }
            if (scrollEvent.dy()<0){
                int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                if (Math.max(firstVisiblePosition - LOADING_THRESHOLD,0) < BeerContainerResponse.RESULTS_COUNT_PER_PAGE*(currentPage-1)){
                    mustBeLoaded = true;
                }
            }
            loading = adapter.isLoading();
            if (mustBeLoaded&&!loading){
                adapter.setLoading(true);
            }
        }
        return mustBeLoaded&&!loading;
    }
}
