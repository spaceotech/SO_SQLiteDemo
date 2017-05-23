package com.example.sqlitedemo;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by sotsys-217 on 9/1/17.
 */

public class EncryptUtils {

    public static String a(String input, String key)
    {
        try
        {
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher localCipher = Cipher.getInstance("AES");
            localCipher.init(1, localSecretKeySpec);
            String str = Base64.encodeToString(localCipher.doFinal(input.getBytes("UTF-8")), 0);
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return input;
    }

    public static String b(String input, String key)
    {
        try
        {
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher localCipher = Cipher.getInstance("AES");
            localCipher.init(2, localSecretKeySpec);
            String str = new String(localCipher.doFinal(Base64.decode(input, 0)), "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return input;
    }
}
