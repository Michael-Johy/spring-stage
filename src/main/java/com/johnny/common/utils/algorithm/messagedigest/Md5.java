package com.johnny.common.utils.algorithm.messagedigest;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 消息摘要算法注意事项：当执行POST请求时，若请求参数中包含有特殊字符或者中文字符，则默认会使用
 * x-www-form-urlencoded 对参数进行URLEncode , 此时接口如果需要签名的话，则需要考虑生成签名：使用
 * urlencoded之前或之后的字符串
 *
 * Created by johnny01.yang on 2016/6/29.
 */
public class Md5 {

    /**
     * 消息摘要算法第5版
     *
     * @param str string to encode
     * @return md5
     */
    public static String md5(String str) {
        String result = null;
        if (StringUtils.isNotBlank(str)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(str.getBytes(), 0, str.length());
                result = String.format("%32X", new BigInteger(1, md5.digest()));
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(Md5.md5("b2c123123090u123").toLowerCase());
    }
}
