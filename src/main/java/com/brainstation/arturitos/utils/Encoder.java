package com.brainstation.arturitos.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    public static String encode (String toEncode)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        md.update(toEncode.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
