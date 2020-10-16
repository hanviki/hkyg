package cc.mrbird.febs.hkgf.utils;

import java.util.Date;

public class CheckRequestUtils {
    public CheckRequestUtils() {
    }

    public static boolean check(String timestamp, String sign) {
        byte[] sings = hexStringToBytes(sign);
        String key = MD5Utils.getMD5("xiehe123456");
        System.out.println(key);
        String result = new String(SecretUtils.decryptMode(sings, key));
        return result.equals(timestamp);
    }

    public static String sign(String timestamp) {
        byte[] times = timestamp.getBytes();
        String key = MD5Utils.getMD5("xiehe123456");
        System.out.println(key);
        printHexString(SecretUtils.encryptMode(times, key));
        System.out.println(bytesToHexString(SecretUtils.encryptMode(times, key)));
        String result = new String(SecretUtils.encryptMode(times, key));
        return result;
    }

    public static void main(String[] args) {
        String date = String.valueOf((new Date()).getTime());
        System.out.println(date);
        sign(date);
    }

    public static String printHexString(byte[] b) {
        String result = "";

        for(int i = 0; i < b.length; ++i) {
            String hex = Integer.toHexString(b[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            result = result + hex.toUpperCase();
        }

        System.out.println("||||" + result);
        return result;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for(int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    private static byte charToByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }
}

