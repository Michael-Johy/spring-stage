package com.johnny.dao;

import com.johnny.entity.myswagger.Swagger;
import org.apache.commons.lang3.StringUtils;

import java.util.Vector;

/**
 * Created by johnny01.yang on 2016/7/21.
 */
public class SwaggerTestDao {

    public static Vector<Swagger> vector = new Vector<>();

    static {
        vector.add(new Swagger("johnny01", 1));
        vector.add(new Swagger("johnny02", 2));
        vector.add(new Swagger("johnny03", 3));
        vector.add(new Swagger("johnny04", 3));
        vector.add(new Swagger("johnny05", 3));
    }

    public Swagger getByName(String name) {
        if (StringUtils.isBlank(name))
            return null;
        for (Swagger swagger : vector) {
            if (swagger.getName().equals(name)) {
                return swagger;
            }
        }
        return null;
    }

    public Swagger getByAge(Integer age) {
        if (age == null || age <= 0) {
            return null;
        }
        for (Swagger swagger : vector) {
            if (swagger.getAge() == age) {
                return swagger;
            }
        }
        return null;
    }

    public boolean createSwagger(Swagger swagger) {
        if (vector.contains(swagger)) {
            return false;
        }
        return vector.add(swagger);
    }

}
