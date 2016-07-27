package com.johnny.resourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by johnny01.yang on 2016/7/27.
 */
public class ResourceBundleTest {

    public static void main(String[] args){
        ResourceBundle rb1 = ResourceBundle.getBundle("message", Locale.US);
        System.out.println(rb1.getString("aaa"));

        ResourceBundle rb2 = ResourceBundle.getBundle("message", Locale.CHINA);
        System.out.println(rb2.getString("aaa"));
    }
}
