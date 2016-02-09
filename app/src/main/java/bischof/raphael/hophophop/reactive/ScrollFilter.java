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
        if(scrollEvent.view().getAdapter() instanceof PagingAdapter){
            PagingAdapter adapter = (PagingAdapter)scrollEvent.view().getAdapter();
            boolean mustLoad = mustBeLoaded(scrollEvent.dx(),
                    scrollEvent.dy(),
                    scrollEvent.view().getAdapter().getItemCount(),
                    adapter.isLoading(),
                    adapter.getCurrentPage());
            if (mustLoad) adapter.setLoading(true);
            return mustLoad;
        }else{
            return false;
        }
    }

    public boolean mustBeLoaded(int dx, int dy, int itemCount, boolean loading, int currentPage){
        boolean mustBeLoaded = false;
        if (dx==0&&dy==0) {
            if (itemCount==0){
                mustBeLoaded = true;
            }
        }else{
            if (dy>0){
                int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                if (Math.min(lastVisiblePosition + LOADING_THRESHOLD, itemCount) > BeerContainerResponse.RESULTS_COUNT_PER_PAGE*(currentPage)){
                    mustBeLoaded = true;
                }
            }
            if (dy<0){
                int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                if (Math.max(firstVisiblePosition - LOADING_THRESHOLD,0) < BeerContainerResponse.RESULTS_COUNT_PER_PAGE*(currentPage-1)){
                    mustBeLoaded = true;
                }
            }
        }
        return mustBeLoaded&&!loading;
    }
}
