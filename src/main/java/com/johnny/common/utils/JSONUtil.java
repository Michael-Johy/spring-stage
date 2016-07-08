package com.johnny.common.utils;

import com.johnny.entity.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * define common JSON Operation
 * <p>
 * Created by johnson.yang on 2016/5/10.
 */
public class JSONUtil {

    private static Gson gson = null;

    static {
        gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Person.class, new PersonAdapter())
                .enableComplexMapKeySerialization()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

    /**
     * to serialize primitive
     *
     * @param object object to serialize
     * @return Json representation of src
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * to serialize non-primitive
     *
     * @param object object to serialize
     * @param type   type of object eg:Map
     * @return Json representation of src
     */
    public static String toJson(Object object, Type type) {
        return gson.toJson(object, type);
    }

    /**
     * to serialize primitive
     *
     * @param content string from which the object to deserialize
     * @param tClass  class of T
     * @param <T>     the type of desired object
     * @return an object of type T
     */
    public static <T> T fromJson(String content, Class<T> tClass) {
        return gson.fromJson(content, tClass);
    }


    private static class PersonAdapter extends TypeAdapter<Person> {
        @Override
        public void write(JsonWriter out, Person value) throws IOException {
            if (null == value) {
                out.nullValue();
                return;
            }
            String xy = value.getName() + ";" + value.getAge();
            out.value(xy);
        }

        @Override
        public Person read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            String xy = in.nextString();
            String[] parts = xy.split(";");
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            return new Person(name, age);
        }
    }

}
