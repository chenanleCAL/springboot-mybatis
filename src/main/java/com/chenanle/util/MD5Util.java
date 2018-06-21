package com.chenanle.util;

import java.security.MessageDigest;

/**
 * Created by chenanle on 2018/6/13.
 */
public class MD5Util {


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            stringBuffer.append(byteToHexString(b[i]));
        }
        return stringBuffer.toString();
    }

    /**
     * 返回大写的MD5
     * @param origin
     * @param charsetname
     * @return
     */
    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {

        }
        return resultString.toUpperCase();
    }

    public static String MD5EncodeUtf8(String origin) {
        return MD5Encode(origin,"utf-8");
    }


}
