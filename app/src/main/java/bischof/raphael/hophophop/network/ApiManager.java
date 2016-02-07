package bischof.raphael.hophophop.network;

import com.google.gson.Gson;

import javax.inject.Inject;

import bischof.raphael.hophophop.model.BeerContainer;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Helper that creates an observable for each Retrofit request on the BreweryDB API
 * Created by rbischof on 03/02/2016.
 */
public class ApiManager implements ApiService {
    public static final String URL = "http://api.brewerydb.com/v2/";
    private ApiService mService;

    @Inject
    public ApiManager(Gson gson) {
        // Asynchronous Call in Retrofit 2.0
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mService = retrofit.create(ApiService.class);
    }

    public Observable<BeerContainer> getBeers(String key, int styleID, int page) {
        return mService.getBeers(key, styleID, page);
    }
}
