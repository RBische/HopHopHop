package bischof.raphael.hophophop.network;

import bischof.raphael.hophophop.model.BeerContainer;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Description of the methods used on the BreweryDB API
 * Created by rbischof on 05/02/2016.
 */
public interface ApiService {
    @GET("beers")
    Observable<BeerContainer> getBeers(@Query("key") String key, @Query("styleId") int styleID, @Query("p") int page);
}
