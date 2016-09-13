package com.johnny.common.utils.algorithm.cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

public class ThreeDESUtils {
    private static final String Algorithm = "DESede";
    public static final String vipKey = "ERERIhERERERERERERERERERERMxERER";

    /**
     * 加密数据
     *
     * @param data 需要加密的数据
     * @param key  密钥
     * @return 加密结果
     */
    public static String encryptMode(String data, String key) {
        if (data == null || data.length() == 0) {
            return "";
        }
        if (key == null || key.length() == 0) {
            throw new UnsupportedOperationException("3DES加密密钥KEY为空!");
        }
        try {
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
            byte[] dataBytes = data.getBytes("UTF-8");

            DESedeKeySpec spec = new DESedeKeySpec(keyBytes);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(Algorithm);
            Key deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] bOut = cipher.doFinal(dataBytes);
            return new BASE64Encoder().encode(bOut);
        } catch (Exception e) {
            throw new UnsupportedOperationException("3DES加密失败");
        }
    }

    /**
     * 解密需要的数据
     *
     * @param data 需要解密的数据
     * @param key  密钥
     * @return 解密后的数据
     */
    public static String decryptMode(String data, String key) {
        if (data == null || data.length() == 0) {
            return "";
        }
        if (key == null || key.length() == 0) {
            throw new UnsupportedOperationException("3DES解密密钥KEY为空!");
        }
        try {
            byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
            byte[] dataBytes = new BASE64Decoder().decodeBuffer(data);

            DESedeKeySpec spec = new DESedeKeySpec(keyBytes);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(Algorithm);
            Key deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, deskey);

            byte[] bOut = cipher.doFinal(dataBytes);
            return new String(bOut, "UTF-8");
        } catch (Exception e) {
            throw new UnsupportedOperationException("3DES解密失败");
        }
    }

    public static void main(String[] args) {
        System.out.println(ThreeDESUtils.encryptMode("90002000001woshiguanliyuan", "90002000001" + vipKey));

        System.out.println(ThreeDESUtils.decryptMode("jNB3LwIi/ZHy5Rbf9TKl5D12ITifI5a3WLmGu8ouoIM=", "90002000001" + vipKey));
    }
//
//    /**
//     * 生成合作方使用密钥
//     *
//     * @return 密钥值
//     */
//    public static String generatePartnerSignKey() {
//        String r = 8 + RandomUtil.generateRandom(4) + DateUtil.getNow17Len();
//        String des = encryptMode(r, vipKey);
//        return Md5EncryptUtil.md5(des);
//    }
}