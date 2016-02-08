package bischof.raphael.hophophop.modules.typeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Adapt a homogeneous realmlist of objects.
 * Based on Gson source code
 * Created by biche on 08/02/2016.
 */
public final class RealmListTypeAdapterFactory implements TypeAdapterFactory {

        public RealmListTypeAdapterFactory() {
        }

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Type type = typeToken.getType();

            Class<? super T> rawType = typeToken.getRawType();
            if (!RealmList.class.isAssignableFrom(rawType)) {
                return null;
            }

            Type elementType = $Gson$Types.getCollectionElementType(type, rawType);
            TypeAdapter<?> elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType));

            @SuppressWarnings({"unchecked", "rawtypes"}) // create() doesn't define a type parameter
                    TypeAdapter<T> result = new Adapter(gson, elementType, elementTypeAdapter);
            return result;
        }

        private static final class Adapter<T extends RealmObject> extends TypeAdapter<RealmList<T>> {

            private final TypeAdapterRuntimeTypeWrapper<T> elementTypeAdapter;

            public Adapter(Gson context, Type elementType,
                                    TypeAdapter<T> elementTypeAdapter) {
                this.elementTypeAdapter =
                        new TypeAdapterRuntimeTypeWrapper<>(context, elementTypeAdapter, elementType);
            }

            @Override
            public void write(JsonWriter out, RealmList<T> value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }

                out.beginArray();
                for (T element : value) {
                    elementTypeAdapter.write(out, element);
                }
                out.endArray();
            }

            @Override
            public RealmList<T> read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }

                RealmList<T> collection = new RealmList<>();
                in.beginArray();
                while (in.hasNext()) {
                    T instance = elementTypeAdapter.read(in);
                    collection.add(instance);
                }
                in.endArray();
                return collection;
            }

        }
}
