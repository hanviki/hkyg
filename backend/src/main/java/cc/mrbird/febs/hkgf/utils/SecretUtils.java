package cc.mrbird.febs.hkgf.utils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecretUtils {
    private static final String Algorithm = "DESede";

    public SecretUtils() {
    }

    public static byte[] encryptMode(byte[] src, String key) {
        try {
            SecretKey desKey = new SecretKeySpec(build3DesKey(key), "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(1, desKey);
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

    public static byte[] decryptMode(byte[] src, String key) {
        try {
            SecretKey desKey = new SecretKeySpec(build3DesKey(key), "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(2, desKey);
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

    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");
        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }

        return key;
    }
}

