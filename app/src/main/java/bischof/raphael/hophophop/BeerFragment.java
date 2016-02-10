package bischof.raphael.hophophop;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
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
public class BeerFragment extends Fragment {
    private static final java.lang.String PAGE_TO_LOAD = "PageToLoad";
    private static final int BEER_STYLE_ID = 30;

    @Bind(R.id.rvBeers)
    public RecyclerView mRvBeers;
    @Bind(R.id.tvBeersEmpty)
    public TextView mTvBeersEmpty;
    private LinearLayoutManager mLayoutManager;

    private Subscription mSubscription;
    private Subscription mDbSubscription;
    private ScrollFilter mScrollFilter;
    private ScrollToPageLoader mScrollToPageLoader;
    private ConnectableObservable<BeerContainerResponse> mConnectableObservable;

    public BeerFragment() {
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
        BeerAdapter rvAdapter = new BeerAdapter(getActivity(), mTvBeersEmpty);
        mRvBeers.setAdapter(rvAdapter);
        int pageToLoadFirst = 1;
        if (savedInstanceState!=null){
            pageToLoadFirst = savedInstanceState.getInt(PAGE_TO_LOAD);
        }
        handleRxLogic(pageToLoadFirst);
    }

    private void handleRxLogic(int pageToLoadFirst) {
        //Creates an observable on RecyclerView scrolling (with handling of back pressure)
        Observable<RecyclerViewScrollEvent> scrollEventsObservable = RxRecyclerView.scrollEvents(mRvBeers).throttleLast(500, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io());
        //Filters RecyclerViewScrollEvent observable to retrieve scroll that fires a page changing
        mScrollFilter = new ScrollFilter(mLayoutManager);
        scrollEventsObservable = scrollEventsObservable.throttleFirst(500, TimeUnit.MILLISECONDS).filter(mScrollFilter);
        //Convert scroll to an API call or a DB data fetch
        mScrollToPageLoader = new ScrollToPageLoader(getContext(), BEER_STYLE_ID, pageToLoadFirst, mLayoutManager);
        Observable<BeerContainerResponse> beerContainerObservable = scrollEventsObservable.flatMap(mScrollToPageLoader).subscribeOn(Schedulers.io());
        //Creates a connectable observable to subscribe DB saver and adapter call
        mConnectableObservable = beerContainerObservable.throttleLast(500, TimeUnit.MILLISECONDS).publish();
        subscribeToConnectable();
    }

    /**
     * Subscribes the connectable to an UI subscription (on main thread) and a subscription that saves data in DB (executed on the same thread as the connectable)
     */
    private void subscribeToConnectable() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        if (mDbSubscription != null){
            mDbSubscription.unsubscribe();
        }
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
                        if (e instanceof IOException) {
                            switchToOfflineMode(true);
                        }
                        mTvBeersEmpty.setText(getString(R.string.sure_connection));
                    }

                    @Override
                    public void onNext(BeerContainerResponse beers) {
                        if (beers != null && mRvBeers.getAdapter() instanceof BeerAdapter) {
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
                        if (beers != null && beers.isFromNetwork()) {
                            beers.setFromNetwork(false);
                            //Sets the styleid (Would be moved in BeerContainerResponse if RealmObjects supported custom methods)
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

    private void switchToOfflineMode(boolean offlineMode) {
        this.mScrollFilter.forceReload();
        this.mScrollToPageLoader.setOfflineMode(offlineMode);
        this.mRvBeers.scrollToPosition(0);
        subscribeToConnectable();
        if(offlineMode&&getView()!=null){
            Snackbar.make(getView(),getString(R.string.no_connection_found),Snackbar.LENGTH_LONG).setAction(getString(R.string.cancel), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToOfflineMode(false);
                }
            }).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (((BeerAdapter) mRvBeers.getAdapter()).getBeerContainer()!=null){
            outState.putInt(PAGE_TO_LOAD, ((BeerAdapter) mRvBeers.getAdapter()).getBeerContainer().getCurrentPage());
        }
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
