package org.bobochen.common;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Aes {

    private static String key="F08D5EA9F5EE48ECBCE3D576A35D06DD";


    public static String Encrypt(String str) {
        try{

            byte[] kb = key.getBytes("utf-8");
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//算法/模式/补码方式
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte[] eb = cipher.doFinal(str.getBytes("utf-8"));
            return new Base64().encodeToString(eb);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //解密
    public  static String Decrypt(String str) {
        try{

            byte[] kb = key.getBytes("utf-8");
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] by = new Base64().decode(str);
            byte[] db = cipher.doFinal(by);
            return new String(db);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) throws Exception
    {

        String en = Aes.Encrypt("432法埃fsfdfe");
        System.out.println("加密:"+en);

        String de = Aes.Decrypt(en);
        System.out.println("解密:"+de);
    }


}
