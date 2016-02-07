package bischof.raphael.hophophop;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import bischof.raphael.hophophop.modules.ApiComponent;
import bischof.raphael.hophophop.modules.ApiModule;
import bischof.raphael.hophophop.modules.DaggerApiComponent;

import static org.junit.Assert.assertEquals;

public class GsonTest {
    private ApiComponent mComponent;

    @Before
    public void setUp() {
        mComponent = DaggerApiComponent.builder().apiModule(new ApiModule(null)).build();
    }

    @Test
    public void gson_serializeAndDeserializeDateCorrectly() throws Exception {
        Gson gson = mComponent.gson();
        SerializationMock mock = new SerializationMock(new DateTime(0));
        String serialization = gson.toJson(mock);
        assertEquals(serialization, "{\"dt\":\"1970-01-01 01:00:00\"}");
        SerializationMock mockDeserialized = gson.fromJson(serialization, SerializationMock.class);
        assertEquals(mockDeserialized.dt, mock.dt);
    }
    private class SerializationMock{
        private DateTime dt;

        public SerializationMock(DateTime dt) {
            this.dt = dt;
        }
    }
}
