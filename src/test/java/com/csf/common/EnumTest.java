package com.csf.common;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-06-27 10:20
 */
public class EnumTest {

    private enum Type1 {
        String("aa"),
        Number("bb"),
        Boolean("cc");

        private String label;

        Type1(String label) {
            this.label = label;
        }

        public static Map<String, Type1> valueMap = Maps.newHashMap();

        static {
            for (Type1 type1 : Type1.values()) {
                valueMap.put(type1.toString(), type1);
            }
        }
    }

    @Test
    public void test() {
        Map<String, Type1> valuesMap = Type1.valueMap;
        valuesMap.keySet().forEach(System.out::println);
    }


}
