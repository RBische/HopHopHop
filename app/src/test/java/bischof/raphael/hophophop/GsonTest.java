package bischof.raphael.hophophop;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import bischof.raphael.hophophop.modules.ApiComponent;
import bischof.raphael.hophophop.modules.ApiModule;
import bischof.raphael.hophophop.modules.DaggerApiComponent;
import io.realm.RealmList;
import io.realm.RealmObject;

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
        DateTimeSerializationMock mock = new DateTimeSerializationMock(new DateTime(0).toDate());
        String serialization = gson.toJson(mock);
        assertEquals("Serialized : "+serialization, serialization, "{\"dt\":\"1970-01-01 01:00:00\"}");
        DateTimeSerializationMock mockDeserialized = gson.fromJson(serialization, DateTimeSerializationMock.class);
        assertEquals("Deserialized : "+mockDeserialized.dt.toString(),mockDeserialized.dt, mock.dt);
    }

    @Test
    public void gson_serializeAndDeserializeRealmListCorrectly() throws Exception {
        Gson gson = mComponent.gson();
        RealmObjectMock mockObject = new RealmObjectMock();
        mockObject.setStr("test");
        RealmList<RealmObjectMock> list = new RealmList<>();
        list.add(mockObject);
        RealmListSerializationMock mockContainer = new RealmListSerializationMock(list);
        String serialization = gson.toJson(mockContainer);
        assertEquals(serialization, "{\"obj\":[{\"str\":\"test\"}]}");
        RealmListSerializationMock mockDeserialized = gson.fromJson(serialization, RealmListSerializationMock.class);
        assertEquals(mockDeserialized.obj.get(0).getStr(), mockContainer.obj.get(0).getStr());
    }

    private class DateTimeSerializationMock {
        private Date dt;

        public DateTimeSerializationMock(Date dt) {
            this.dt = dt;
        }
    }

    private class RealmListSerializationMock {
        private RealmList<RealmObjectMock> obj;

        public RealmListSerializationMock(RealmList<RealmObjectMock> obj) {
            this.obj = obj;
        }
    }
    private class RealmObjectMock extends RealmObject {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
