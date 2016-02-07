package bischof.raphael.hophophop;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import bischof.raphael.hophophop.adapter.BeerAdapter;
import bischof.raphael.hophophop.adapter.PagingAdapter;
import bischof.raphael.hophophop.model.BeerContainer;
import bischof.raphael.hophophop.modules.ApiComponent;
import bischof.raphael.hophophop.modules.ApiModule;
import bischof.raphael.hophophop.modules.DaggerApiComponent;
import bischof.raphael.hophophop.recyclerview.RecyclerViewScrollEvent;
import bischof.raphael.hophophop.recyclerview.RxRecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Fragment containing the list of beers
 */
public class BeerActivityFragment extends Fragment {
    private Subscription mSubscription;
    @Bind(R.id.rvBeers)
    public RecyclerView mRvBeers;
    @Bind(R.id.tvBeersEmpty)
    public TextView mTvBeersEmpty;

    private LinearLayoutManager mLayoutManager;

    public BeerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beer, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRvBeers.setLayoutManager(mLayoutManager);
        mRvBeers.setAdapter(new BeerAdapter(getActivity(), mTvBeersEmpty));
        handleRxLogic();
    }

    private void handleRxLogic() {
        //TODO: Unit test on observables
        // For filter and retrofit call (scroll -> instantation, scroll+&-)
        Observable<RecyclerViewScrollEvent> scrollEventsObservable = RxRecyclerView.scrollEvents(mRvBeers).throttleFirst(500, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io());
        scrollEventsObservable = scrollEventsObservable.filter(new Func1<RecyclerViewScrollEvent, Boolean>() {
            private int loadingThreshold = 15;
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
                        if (Math.min(lastVisiblePosition + loadingThreshold, itemCount) >BeerContainer.RESULTS_COUNT_PER_PAGE*(currentPage)){
                            mustBeLoaded = true;
                        }
                    }
                    if (scrollEvent.dy()<0){
                        int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                        if (Math.max(firstVisiblePosition - loadingThreshold,0) <BeerContainer.RESULTS_COUNT_PER_PAGE*(currentPage-1)){
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
        });
        Observable<BeerContainer> beerContainerObservable = scrollEventsObservable.flatMap(new Func1<RecyclerViewScrollEvent, Observable<BeerContainer>>() {
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
                ApiComponent component = DaggerApiComponent.builder().apiModule(new ApiModule(getContext())).build();
                return component.manager().getBeers("595f9b678d9a88434f7e5c72f8cfac38", 30, pageToLoad);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //TODO: Move key in apimanager constructor, use dagger to inject ApiManager -> Can easily mock it
        mSubscription = beerContainerObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BeerContainer>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error", e);
                    }

                    @Override
                    public void onNext(BeerContainer forecast) {
                        if (mRvBeers.getAdapter() instanceof BeerAdapter) {
                            BeerAdapter rvAdapter = (BeerAdapter) mRvBeers.getAdapter();
                            rvAdapter.changeBeers(forecast);
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
