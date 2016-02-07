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
import bischof.raphael.hophophop.model.BeerContainer;
import bischof.raphael.hophophop.reactive.RecyclerViewScrollEvent;
import bischof.raphael.hophophop.reactive.RxRecyclerView;
import bischof.raphael.hophophop.reactive.ScrollFilter;
import bischof.raphael.hophophop.reactive.ScrollToPageLoader;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Fragment containing the list of beers
 * Created by biche on 05/02/2016.
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
        //TODO: Move key in apimanager constructor, use dagger to inject ApiManager -> Can easily mock it
        //Creates an observable on RecyclerView scrolling (with handling of back pressure)
        Observable<RecyclerViewScrollEvent> scrollEventsObservable = RxRecyclerView.scrollEvents(mRvBeers).throttleFirst(500, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io());
        //Filters RecyclerViewScrollEvent observable to retrieve scroll that fires a page changing
        scrollEventsObservable = scrollEventsObservable.filter(new ScrollFilter(mLayoutManager));
        //Convert scroll to an API call or a DB data fetch
        Observable<BeerContainer> beerContainerObservable = scrollEventsObservable.flatMap(new ScrollToPageLoader(getContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        mSubscription = beerContainerObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
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
