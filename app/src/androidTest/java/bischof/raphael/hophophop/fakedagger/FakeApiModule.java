package bischof.raphael.hophophop.fakedagger;

import android.content.Context;

import com.google.gson.Gson;

import bischof.raphael.hophophop.modules.ApiModule;
import bischof.raphael.hophophop.network.ApiManager;

/**
 * Faking retrieving data from ApiModule
 * Created by biche on 09/02/2016.
 */
public class FakeApiModule extends ApiModule {
    public FakeApiModule(Context mContext) {
        super(mContext);
    }

    @Override
    public ApiManager provideManager(Gson gson) {
        return new FakeApiManager(gson,"");
    }
}
