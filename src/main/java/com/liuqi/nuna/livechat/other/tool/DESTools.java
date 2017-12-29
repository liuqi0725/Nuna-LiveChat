package com.liuqi.nuna.livechat.other.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午3:58 2017/12/29
 */
public class DESTools {

    private byte[] desKey;

    public DESTools(String desKey) {
        this.desKey = desKey.getBytes();
    }

    private byte[] desEncrypt(byte[] plainText) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte data[] = plainText;
        byte encryptedData[] = cipher.doFinal(data);
        return encryptedData;
    }

    private byte[] desDecrypt(byte[] encryptText) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return decryptedData;
    }

    public String encrypt(String input) throws Exception {
        return base64Encode(desEncrypt(input.getBytes()));
    }

    public String decrypt(String input) throws Exception {
        byte[] result = base64Decode(input);
        return new String(desDecrypt(result));
    }

    private static String base64Encode(byte[] s) {
        if (s == null)
            return null;
        BASE64Encoder b = new sun.misc.BASE64Encoder();
        return b.encode(s);
    }

    private static byte[] base64Decode(String s) throws IOException {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(s);
        return b;
    }

    public static void main(String[] args) throws Exception {
        String key = "nuna-live-chat";
        String input = "{\"site_domain\":\"localhost\",\"id\":\"1\",\"uid\":\"1\"}";
        DESTools crypt = new DESTools(key);
        String enStr = crypt.encrypt(input).replaceAll("\r|\n", "");
        System.out.println("Encode:" + enStr);
        System.out.println("Decode:" + crypt.decrypt(enStr));
        System.out.println("http://wwww.baidu.com?dd=" + enStr);
    }

}
