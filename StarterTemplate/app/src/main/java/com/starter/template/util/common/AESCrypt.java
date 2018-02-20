package com.starter.template.util.common;

import android.text.TextUtils;
import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AESCrypt {

    private static final String IV_PARAM = "bMSWvFj4B3ZgXvwz";
    private static final String SECRET_KEY = "ZHw9HrWzY6zyD3L8";
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";

    public static String encrypt(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        Cipher cipher;
        String encryptedString = null;
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(IV_PARAM.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
            cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] cipherText = cipher.doFinal(value.getBytes());
            encryptedString = Base64.encodeToString(cipherText, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException
                | IllegalBlockSizeException
                | NoSuchPaddingException
                | BadPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static String decrypt(String cipherText) {
        if (TextUtils.isEmpty(cipherText)) {
            return null;
        }
        Cipher cipher = null;
        String decryptString = null;
        try {
            byte[] encryptedText = Base64.decode(cipherText, Base64.DEFAULT);
            IvParameterSpec ivSpec = new IvParameterSpec(IV_PARAM.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
            cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] cipherTextStr = cipher.doFinal(encryptedText);
            decryptString = new String(cipherTextStr);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException
                | InvalidAlgorithmParameterException
                | InvalidKeyException e) {
            e.printStackTrace();
        }
        return decryptString;
    }
}
