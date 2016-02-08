package bischof.raphael.hophophop.modules;

import android.content.Context;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;
import java.util.Date;

import bischof.raphael.hophophop.R;
import bischof.raphael.hophophop.modules.typeadapter.RealmListTypeAdapterFactory;
import bischof.raphael.hophophop.network.ApiManager;
import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;

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
    static Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                return new DateTime(formatter.parseDateTime(json.getAsJsonPrimitive().getAsString())).toDate();
            }
        });
        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                return new JsonPrimitive(formatter.print(new DateTime(src)));
            }
        });

        // Register an adapter to manage RealmList
        builder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        builder.registerTypeAdapterFactory(new RealmListTypeAdapterFactory());

        return builder.create();
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