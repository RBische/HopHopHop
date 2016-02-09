package bischof.raphael.hophophop.component;

import com.google.gson.Gson;

import bischof.raphael.hophophop.modules.ApiModule;
import bischof.raphael.hophophop.modules.GsonModule;
import bischof.raphael.hophophop.network.ApiManager;
import dagger.Component;

/**
 * Component used in production with dagger
 * Created by biche on 06/02/2016.
 */
@Component(modules = {ApiModule.class, GsonModule.class})
public interface AppComponent {
    ApiManager manager();
    Gson gson();
}
