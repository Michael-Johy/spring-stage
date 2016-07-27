package com.csf.common;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import org.apache.http.NameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.beans.propertyeditors.CharsetEditor;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by johnson.yang on 2016/5/11.
 */
public class CommonTest {

    List<Student> students = ImmutableList.of(new Student("aa1", 1, new Date()),
            new Student("aa2", 2, new Date()), new Student("bb3", 3, new Date()));

    @Before
    public void setUp() {
    }

    @Test
    public void testDemo1() {
        Predicate<Student> predicate = predicate("aa");
        students.stream().filter(predicate).forEach(System.out::println);
    }

    private Predicate<Student> predicate(String prefix) {
        return a -> a.getName().startsWith(prefix);
    }

    @Test
    public void test3() {
        AbstractBeanFactory abstractBeanFactory = null;
        BeanFactory beanFactory = null;
        BeanWrapper wrapper = null;
        CharsetEditor editor = null;
        Table<String, String, String> table = HashBasedTable.create();
        table.put("1", "2", "3");
        table.put("1", "2", "4");

        System.out.println(table.get("1", null));
    }

    @Test
    public void test4() {
        NameValuePair nameValuePair = new NameValuePair() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getValue() {
                return null;
            }
        };
    }

    @Test
    public void test5() {
        MessageFormat mf = new MessageFormat("{0}, {1}");
        Object[] objs = new Object[]{"aa", "bb"};
        System.out.println(mf.format(objs));
        DefaultListableBeanFactory factory = null;
        XmlBeanFactory factory1 = null;
    }

    @Test
    public void test6() {
//        Class<?> CLASS = DefaultListableBeanFactory.class;
//        String CLASSNAME = CLASS.getSimpleName();
//        System.out.println(CLASSNAME);
//        XmlBeanDefinitionReader reader = null;
//        Collection collection = null;

        String abc = "你好,";
        System.out.println("length：" + abc.length());
        System.out.println("Util length:");
        byte[] bytes = null;
        try {
            bytes = abc.getBytes("UTF-8");
        } catch (Exception e) {

        }
        if (bytes != null) {
            System.out.println("Byte size: " + bytes.length);
        }

        Class t = String.class;
        System.out.println("Name:" + t.getName());
    }

    @Test
    public void test7() {
        Boolean valid = new Boolean(true);
        inValid(valid);
        System.out.println(valid);
    }

    private void inValid(Boolean valid) {
        valid = false;
    }

    @Test
    public void test8() {
        Date date = new Date();
        final String SYNC_RESULT_EMAIL_SUBJECT = "资产转移系统%d年%d月%d日结算情况";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year);
        System.out.println(String.format(SYNC_RESULT_EMAIL_SUBJECT, year, month, day));
    }


}
