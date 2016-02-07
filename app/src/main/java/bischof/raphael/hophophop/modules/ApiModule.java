package bischof.raphael.hophophop.modules;

import android.content.Context;

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
    static Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(DateTime.class, new JsonDeserializer<DateTime>() {
            public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                return new DateTime(formatter.parseDateTime(json.getAsJsonPrimitive().getAsString()));
            }
        });
        builder.registerTypeAdapter(DateTime.class, new JsonSerializer<DateTime>() {
            @Override
            public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                return new JsonPrimitive(formatter.print(src));
            }
        });

        return builder.create();
    }

    @Provides
    static ApiManager provideManager(Gson gson) {
        return new ApiManager(gson);
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}