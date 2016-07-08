package com.johnny.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johnny01.yang on 2016/6/29.
 */
public class PhoneNoUtils {

    /**
     * 验证手机号码合法性
     * 　移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * <p>
     * 　　联通：130、131、132、152、155、156、185、186
     * <p>
     * 　　电信：133、153、180、189、（1349卫通）
     *
     * @param phoneNo phoneNo string
     * @return true valid, otherwise false
     */
    public static boolean isMobileNo(String phoneNo) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(PhoneNoUtils.isMobileNo("13611812206"));
    }


}
