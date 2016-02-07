package bischof.raphael.hophophop.modules;

import com.google.gson.Gson;

import bischof.raphael.hophophop.network.ApiManager;
import dagger.Component;

/**
 * Component used to create API class instances
 * Created by biche on 06/02/2016.
 */
@Component(modules = ApiModule.class)
public interface ApiComponent {
    ApiManager manager();
    Gson gson();
}
