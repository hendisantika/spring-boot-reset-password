package com.hendisantika.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 19.42
 */
@Slf4j
public abstract class ResetPasswordDemoUtil {
    // ===============SHY2 method ====================
    /**
     * Method will encrypt a given string with SHY2 algorithm
     * @param password
     * @return {@link String}
     */
    public static String encryptSHY2(String password) {
        MessageDigest md;
        StringBuffer hexString = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    // ===============String salt method====================
    /**
     * Method will create a salt String
     * @return {@link String}
     */
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();

    }
}
