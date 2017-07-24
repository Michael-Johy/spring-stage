package com.johnny.jdk.classLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-18 10:28
 */
public class ClassLoaderTest {

    //name to code
    static Map<String, String> name2Code = new HashMap<>();

    public static void main(String[] args) {
        String code = "public class RuleExample {\n" +
                "    public void execute(Person person, String name, Integer age) {\n" +
                "        person.setName(name);\n" +
                "        person.setAge(age);\n" +
                "    }\n" +
                "}";

        name2Code.put("RuleExample", code);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, code);
        if (result == 0) {
            System.out.println("compiler ok");
        }

        ClassLoader classLoader = new CustomClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass("RuleExample");
            Method method = clazz.getMethod("execute");
            Person aa = new Person();
            method.invoke(clazz.newInstance(), aa, "pp", new Integer(11));
            System.out.println("Name:" + aa.getName());
            System.out.println("Age:" + aa.getAge());
        } catch (Exception e) {
            System.out.println("load error");
        }


    }

    static class CustomClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            System.out.println(name2Code.get(name));
            byte[] bytes = name2Code.get(name).getBytes(Charset.forName("UTF-8"));
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}







