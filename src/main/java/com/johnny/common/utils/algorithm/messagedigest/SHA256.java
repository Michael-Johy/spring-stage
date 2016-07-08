package com.johnny.common.utils.algorithm.messagedigest;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Secure Hash Algorithm is mainly used in DSA(Digital Signature Algorithm) defined in DSS(Digital Signature Standard)
 *
 * Created by johnny01.yang on 2016/7/8.
 */
public class SHA256 {

    /**
     * 消息摘要算法SHA-256
     *
     * @param str string to encode
     * @return SHA-256
     */
    public static String sha256(String str) {
        String result = null;
        if (StringUtils.isNotBlank(str)) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(str.getBytes(), 0, str.length());
                // 格式化成64位小写的16进制数据
                result = String.format("%064x", new BigInteger(1, md.digest()));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(SHA256.sha256("aa"));
    }
}
