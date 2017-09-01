package main;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/7/12
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */

public class Other {

    public static void main(String[] args) {
        //System.out.println(bin(-1L));

        String s = "Test";
        StringBuilder builder = new StringBuilder();
        for (byte b : s.getBytes()) {
            builder.append(String.format("%02X",b));
        }

        System.out.println(byteToHex(s.getBytes()));
        System.out.println(bytesToHex(s.getBytes()));
    }

    public static String  bin(Long n) {
        if (n == null) {
            return null;
        }

        char[] bytes = new char[64];
        Long m = n;
        int len = 0;
        do {
            len += 1;
            bytes[bytes.length - len] =  (m & 1) == 1 ? '1' : '0';
            m >>>= 1;
        } while (m != 0);

        return new String(bytes,bytes.length - len, len);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    private static String byteToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }

        return builder.toString();
    }
}
