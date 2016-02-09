package bischof.raphael.hophophop.modules;

import android.content.Context;

import com.google.gson.Gson;

import bischof.raphael.hophophop.R;
import bischof.raphael.hophophop.network.ApiManager;
import dagger.Module;
import dagger.Provides;

/**
 * Provider of API specific classes
 * Created by biche on 06/02/2016.
 */
@Module
public class ApiModule {

    private Context mContext;

    public ApiModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public ApiManager provideManager(Gson gson) {
        return new ApiManager(gson,mContext.getString(R.string.api_key));
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}