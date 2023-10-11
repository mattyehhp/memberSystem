package com.mattyeh.memberSystem.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        StringBuffer encryptedPassword = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        //
        md.update(password.getBytes());
        byte[] digest = md.digest();
        System.out.println(digest);
        for (int i = 0;i<digest.length;i++) {
            encryptedPassword.append(Integer.toHexString(0xFF & digest[i]));
        }
        return encryptedPassword.toString();
    }

}
