package com.johnny.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by johnny01.yang on 2016/6/20.
 */
public class IPUtil {

    /**
     * 获取IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.trim().length() > 0) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 验证给定的字符串是否是合法的IP地址
     * A 类: [1.0.0.0, 126.255.255.255]
     * B 类: [128.0.0.0, 191.255.255.255]
     * C 类: [192.0.0.0, 223.255.255.255]
     * D 类：[224.0.0.0, 239.255.255.255]
     * E 类: [240.0.0.0, 247.255.255.255]
     *
     * @param str ip string
     * @return true for ok , else false
     */
    public static boolean validIpAddress(String str) {
        String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(IPUtil.validIpAddress("192.168.0.1"));
    }
}
