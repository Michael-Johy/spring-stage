package com.csf.common;

import com.google.common.base.Charsets;
import org.junit.Test;

import java.util.Base64;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-25 13:29
 */
public class Base64Test {

    @Test
    public void test() {
        String info = "Hello Base64Test";
        byte[] base64Bytes = Base64.getEncoder().encode(info.getBytes(Charsets.UTF_8));
        String base64Str = new String(base64Bytes, Charsets.UTF_8);
        byte[] bytes = Base64.getDecoder().decode(base64Str.getBytes(Charsets.UTF_8));
        String decodeInfo = new String(bytes, Charsets.UTF_8);
        System.out.println("DecodeInfo:" + decodeInfo);
    }

    



}
