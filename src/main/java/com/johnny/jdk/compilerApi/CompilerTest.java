package com.johnny.jdk.compilerApi;

import com.johnny.entity.Person;
import com.johnny.jdk.compilerApi.source.RuleExecute;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-20 19:35
 */
public class CompilerTest {
    public static void aaa(String[] args) throws Exception {
        String sourceCode = "package com.johnny.jdk.compilerApi;\n" +
                "/**\n" +
                " * Description:\n" +
                " * <p>\n" +
                " * Author: yang_tao\n" +
                " * Date  : 2017-07-20 19:36\n" +
                " */\n" +
                "public class Test {\n" +
                "\n" +
                "    public void print(){\n" +
                "        System.out.println(\"aaaa\");\n" +
                "    }\n" +
                "\n" +
                "}";

//        String sourceCode = "package com.johnny.jdk.compilerApi;\n" +
//                "/**\n" +
//                " * Description:\n" +
//                " * <p>\n" +
//                " * Author: yang_tao\n" +
//                " * Date  : 2017-07-20 19:36\n" +
//                " */\n" +
//                "\n" +
//                "\n" +
//                "    public void print(){\n" +
//                "        System.out.println(\"aaaa\");\n" +
//                "    }\n" +
//                "\n" +
//                "";
        CustomerCompiler compiler = new CustomerCompiler(CompilerTest.class.getClassLoader(), Arrays.asList("-target", "1.8"));
        //编译代码并得到对应Class实例
        Class<?> clazz = compiler.compile("com.johnny.jdk.compilerApi.Test", sourceCode);
        //创建实例
        Object object = clazz.newInstance();
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            method.invoke(object);
        }
    }

    public static void main(String[] args) throws Exception {
        String sourceCode = "\n" +
                "import com.johnny.jdk.compilerApi.source.RuleExecute;\n" +
                "import com.johnny.entity.Person;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "\n" +
                "/**\n" +
                " * Description:\n" +
                " * <p>\n" +
                " * Author: yang_tao\n" +
                " * Date  : 2017-07-24 16:03\n" +
                " */\n" +
                "public class ${RuleNo} implements RuleExecute {\n" +
                "\n" +
                "    private static final Logger logger = LoggerFactory.getLogger(${RuleNo}.class);\n" +
                "\n" +
                "    @Override\n" +
                "    public void execute(Person person) {\n" +
                "        person.setAge(11);\n" +
                "        person.setName(\"yang_tao\");\n" +
                "        logger.info(\"Person:[\" + person.getName() + \",\" + person.getAge() + \"]\");\n" +
                "    }\n" +
                "}";

        CustomerCompiler compiler = new CustomerCompiler(CompilerTest.class.getClassLoader(), Arrays.asList("-target", "1.8"));
        //编译代码并得到对应Class实例

        String ruleNo = "Rule01";
        String resultCode = sourceCode.replace("${RuleNo}", ruleNo);

        Class<?> clazz = compiler.compile("".concat(ruleNo), resultCode);
        RuleExecute ruleExecute = (RuleExecute) clazz.newInstance();

        Person person = new Person();
        ruleExecute.execute(person);
        System.out.println("Sout Name:" + person.getName());
        System.out.println("Sout Age:" + person.getAge());


    }
}
