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
import bischof.raphael.hophophop.model.BeerContainerResponse;
import bischof.raphael.hophophop.reactive.RecyclerViewScrollEvent;
import bischof.raphael.hophophop.reactive.RxRecyclerView;
import bischof.raphael.hophophop.reactive.ScrollFilter;
import bischof.raphael.hophophop.reactive.ScrollToPageLoader;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Fragment containing the list of beers
 * Created by biche on 05/02/2016.
 */
public class BeerActivityFragment extends Fragment {
    private Subscription mSubscription;
    private Subscription mDbSubscription;
    @Bind(R.id.rvBeers)
    public RecyclerView mRvBeers;
    @Bind(R.id.tvBeersEmpty)
    public TextView mTvBeersEmpty;

    private LinearLayoutManager mLayoutManager;
    private ConnectableObservable<BeerContainerResponse> mConnectableObservable;

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
        // For filter and retrofit call (scroll -> instantation, scroll+&-)
        //Creates an observable on RecyclerView scrolling (with handling of back pressure)
        Observable<RecyclerViewScrollEvent> scrollEventsObservable = RxRecyclerView.scrollEvents(mRvBeers).throttleFirst(500, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io());
        //Filters RecyclerViewScrollEvent observable to retrieve scroll that fires a page changing
        scrollEventsObservable = scrollEventsObservable.throttleFirst(500, TimeUnit.MILLISECONDS).filter(new ScrollFilter(mLayoutManager));
        //Convert scroll to an API call or a DB data fetch
        Observable<BeerContainerResponse> beerContainerObservable = scrollEventsObservable.flatMap(new ScrollToPageLoader(getContext(), 30)).subscribeOn(Schedulers.io());
        //Creates a connectable observable to subscribe DB saver and adapter call
        mConnectableObservable = beerContainerObservable.publish();
        mSubscription = mConnectableObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BeerContainerResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error", e);
                        BeerAdapter rvAdapter = (BeerAdapter) mRvBeers.getAdapter();
                        rvAdapter.setLoading(false);
                    }

                    @Override
                    public void onNext(BeerContainerResponse beers) {
                        if (mRvBeers.getAdapter() instanceof BeerAdapter) {
                            BeerAdapter rvAdapter = (BeerAdapter) mRvBeers.getAdapter();
                            rvAdapter.changeBeers(beers);
                        }
                    }
                });
        mDbSubscription = mConnectableObservable
                .subscribe(new Subscriber<BeerContainerResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error", e);
                    }

                    @Override
                    public void onNext(BeerContainerResponse beers) {
                        if (beers.isFromNetwork()) {
                            beers.setFromNetwork(false);
                            //Sets the styleid
                            if (beers.getData() != null && beers.getData().size() > 0) {
                                if (beers.getData().get(0).getStyle() != null) {
                                    beers.setStyleId(beers.getData().get(0).getStyle().getId());
                                } else {
                                    beers.setStyleId(0);
                                }
                            }
                            Realm realm = Realm.getInstance(getContext());
                            // Copy the object to Realm
                            realm.beginTransaction();
                            realm.copyToRealm(beers);
                            realm.commitTransaction();
                        }
                    }
                });
        mConnectableObservable.connect();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        if (mDbSubscription != null){
            mDbSubscription.unsubscribe();
        }
    }
}
