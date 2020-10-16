package cc.mrbird.febs.hkgf.utils;

import com.sun.crypto.provider.SunJCE;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDES {
    private static final String Algorithm = "DESede";

    public ThreeDES() {
    }

    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(1, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (NoSuchPaddingException var5) {
            var5.printStackTrace();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return null;
    }

    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(2, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (NoSuchPaddingException var5) {
            var5.printStackTrace();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return null;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }

            if (n < b.length - 1) {
                hs = hs + ":";
            }
        }

        return hs.toUpperCase();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Security.addProvider(new SunJCE());
        byte[] keyBytes = SecretUtils.build3DesKey(MD5Utils.getMD5("xiehe123456"));
        String szSrc = "1515141264198";
        String key = new String(keyBytes);
        System.out.println(key);
        System.out.println("加密前的字符串:" + szSrc);
        byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
        String result = bytesToHexString(encoded);
        System.out.println("加密后的字符串:" + result);
        byte[] srcBytes = decryptMode(keyBytes, hexStringToBytes(result));
        System.out.println("解密后的字符串:" + new String(srcBytes));
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

