package bischof.raphael.hophophop;

import android.app.Application;

import bischof.raphael.hophophop.component.AppComponent;
import bischof.raphael.hophophop.component.DaggerAppComponent;
import bischof.raphael.hophophop.modules.ApiModule;

/**
 * Override of the application to support dagger
 * Created by biche on 09/02/2016.
 */
public class HopHopHopApplication extends Application {
    private static HopHopHopApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        appComponent = DaggerAppComponent.builder().apiModule(new ApiModule(this)).build();
    }

    public static HopHopHopApplication getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setMockMode(ApiModule module) {
        appComponent = DaggerAppComponent.builder().apiModule(module).build();
    }
}
