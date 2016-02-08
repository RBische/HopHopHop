package bischof.raphael.hophophop.network;

import com.google.gson.Gson;

import javax.inject.Inject;

import bischof.raphael.hophophop.model.BeerContainerResponse;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Helper that creates an observable for each Retrofit request on the BreweryDB API
 * Created by rbischof on 03/02/2016.
 */
public class ApiManager {
    public static final String URL = "http://api.brewerydb.com/v2/";
    private ApiService mService;
    private String mApiKey;

    @Inject
    public ApiManager(Gson gson, String apiKey) {
        // Asynchronous Call in Retrofit 2.0
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.mService = retrofit.create(ApiService.class);
        this.mApiKey = apiKey;
    }

    public Observable<BeerContainerResponse> getBeers(int styleID, int page) {
        return mService.getBeers(mApiKey, styleID, page, "Y");
    }
}
