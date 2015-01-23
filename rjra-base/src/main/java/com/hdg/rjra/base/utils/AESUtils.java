package com.hdg.rjra.base.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Rock on 2015/1/14 0014.
 */
public class AESUtils {

    private static KeyGenerator kgen;
    private static Cipher cipher;

    static {
        try {
            kgen = KeyGenerator.getInstance("AES");
            cipher = Cipher.getInstance("AES");// 创建密码器
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 加密
     *
     * @param password 需要加密的内容
     * @param userName  加密名称
     * @param userKey  加密因子
     * @return
     */
    public static String encrypt(String password, String userName, String userKey) {
        try {

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((userName + userKey).getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            byte[] byteContent = password.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result); // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param password  待解密内容
     * @param userName 解密名称
     * @param userKey 解密因子
     * @return
     */
    public static String decrypt(String password, String userName, String userKey) {
        try {
            byte[] decryptFrom = parseHexStr2Byte(password);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed((userName+userKey).getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(decryptFrom);
            return new String(result, "utf-8"); // 加密
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        String username = "222"; //加密因子
        String password = "222";
        String key="love7758521";
        //加密
        for (int i = 0; i < 10; i++) {

            System.out.println("加密前：" + password);
            String encryptResultStr = AESUtils.encrypt(password, username, key);
            System.out.println("加密后：" + encryptResultStr);
            //解密
//            String decryptResult = decrypt(encryptResultStr, username, key);
//            System.out.println("解密后：" + decryptResult);
        }


//        String decryptResult = decrypt("8AA1C36BC0CB20A678F88326178A1AB4", "222", "love7758521");
//        System.out.println("解密后：" + decryptResult);
    }
}
